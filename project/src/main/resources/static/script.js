// =====================================================
// ADD EXPENSE
// =====================================================

document.getElementById("expenseForm").addEventListener("submit", async function (event) {

    event.preventDefault();

    const amount = document.getElementById("expenseAmount").value;
    const category = document.getElementById("expenseCategory").value;
    const date = document.getElementById("expenseDate").value;
    const userId = document.getElementById("expenseUserId")
        ? document.getElementById("expenseUserId").value
        : 1;

    const expenseData = {
        amount: Number(amount),
        category: category,
        date: date,
        userId: Number(userId)
    };

    console.log("Sending expense:", expenseData);

    try {

        const response = await fetch("/api/expense/create", {

            method: "POST",

            headers: {
                "Content-Type": "application/json"
            },

            body: JSON.stringify(expenseData)

        });

        if (!response.ok) {

            throw new Error(
                "Failed to add expense. Status: " + response.status
            );

        }

        const data = await response.json();

        console.log("Expense added:", data);

        alert("Expense added successfully!");

        // Check whether spending has reached 90% of budget
        await checkBudgetAlert(category);

        document.getElementById("expenseForm").reset();

    }

    catch (error) {

        console.error("Expense error:", error);

        alert("Error adding expense");

    }

});


// =====================================================
// ADD BUDGET
// =====================================================

document.getElementById("budgetForm").addEventListener("submit", async function (event) {

    event.preventDefault();

    const amount = document.getElementById("budgetAmount").value;
    const category = document.getElementById("budgetCategory").value;
    const month = document.getElementById("budgetMonth").value;
    const userId = document.getElementById("budgetUserId").value;

    const budgetData = {

        amount: Number(amount),
        category: category,
        month: month,
        userId: Number(userId)

    };

    console.log("Sending budget:", budgetData);

    try {

        const response = await fetch("/api/budget/create", {

            method: "POST",

            headers: {
                "Content-Type": "application/json"
            },

            body: JSON.stringify(budgetData)

        });

        if (!response.ok) {

            throw new Error(
                "Failed to add budget. Status: " + response.status
            );

        }

        const data = await response.json();

        console.log("Budget added:", data);

        alert("Budget added successfully!");

        document.getElementById("budgetForm").reset();

        loadBudgets();

    }

    catch (error) {

        console.error("Budget error:", error);

        alert("Error adding budget");

    }

});


// =====================================================
// GET ALL BUDGETS
// =====================================================

async function loadBudgets() {

    const budgetList = document.getElementById("budgetList");

    try {

        const response = await fetch("/api/budget/getall");

        if (!response.ok) {

            throw new Error(
                "Failed to load budgets. Status: " + response.status
            );

        }

        const budgets = await response.json();

        budgetList.innerHTML = "";

        if (!budgets || budgets.length === 0) {

            budgetList.innerHTML =
                '<div class="no-data">No budgets found.</div>';

            return;

        }

        budgets.forEach(function (budget) {

            const div = document.createElement("div");

            div.className = "budget-item";

            div.innerHTML = `

                <p>
                    <strong>ID:</strong>
                    ${budget.id ?? "-"}
                </p>

                <p>
                    <strong>Amount:</strong>
                    ₹${budget.amount ?? 0}
                </p>

                <p>
                    <strong>Category:</strong>
                    ${budget.category ?? "-"}
                </p>

                <p>
                    <strong>Month:</strong>
                    ${budget.month ?? "-"}
                </p>

                <p>
                    <strong>User ID:</strong>
                    ${budget.userId ?? "-"}
                </p>

            `;

            budgetList.appendChild(div);

        });

    }

    catch (error) {

        console.error("Budget loading error:", error);

        budgetList.innerHTML =
            '<div class="no-data">Could not load budgets.</div>';

    }

}


// =====================================================
// 90% BUDGET ALERT
// =====================================================

async function checkBudgetAlert(category) {

    try {

        // Get all expenses
        const expenseResponse =
            await fetch("/api/expense/getall");

        if (!expenseResponse.ok) {

            throw new Error("Could not get expenses");

        }

        const expenses =
            await expenseResponse.json();


        // Get all budgets
        const budgetResponse =
            await fetch("/api/budget/getall");

        if (!budgetResponse.ok) {

            throw new Error("Could not get budgets");

        }

        const budgets =
            await budgetResponse.json();


        // Find budget for the category
        const budget = budgets.find(function (b) {

            return b.category &&
                b.category.toLowerCase() ===
                category.toLowerCase();

        });


        // No budget found for this category
        if (!budget) {

            console.log(
                "No budget found for category: " + category
            );

            return;

        }


        // Calculate total spending
        // for this category
        let totalSpent = 0;

        expenses.forEach(function (expense) {

            if (
                expense.category &&
                expense.category.toLowerCase() ===
                category.toLowerCase()
            ) {

                totalSpent += Number(expense.amount);

            }

        });


        // Calculate 90% of budget
        const budgetAmount = Number(budget.amount);

        const alertLimit = budgetAmount * 0.90;


        console.log("Category:", category);
        console.log("Budget:", budgetAmount);
        console.log("Spent:", totalSpent);
        console.log("90% limit:", alertLimit);


        // Check if spending reached 90%
        if (totalSpent >= alertLimit) {

            alert(
                "⚠️ BUDGET ALERT!\n\n" +
                "Category: " + category + "\n" +
                "Budget: ₹" + budgetAmount + "\n" +
                "Spent: ₹" + totalSpent + "\n\n" +
                "You have used 90% or more of your budget!"
            );

        }

    }

    catch (error) {

        console.error(
            "Budget alert error:",
            error
        );

    }

}


// =====================================================
// LOAD BUDGETS WHEN PAGE OPENS
// =====================================================

document.addEventListener(
    "DOMContentLoaded",
    async function () {

        await loadBudgets();

    }
);