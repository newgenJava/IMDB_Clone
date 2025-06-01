document.addEventListener("DOMContentLoaded", async function () {
    const token = localStorage.getItem("token");

    if (!token) {
        alert("You are not logged in! Redirecting to login.");
        window.location.href = "/user/login";
        return;
    }

    try {
        const response = await fetch("/user/users/me", {
            method: "GET",
            headers: {
                "Authorization": `Bearer ${token}`,
                "Content-Type": "application/json"
            }
        });

        if (response.ok) {
            const user = await response.json();
            document.getElementById("userFullName").textContent = user.fullName;
            document.getElementById("userEmail").textContent = user.email;
            document.getElementById("userCreatedAt").textContent = new Date(user.createdAt).toLocaleString();
        } else {
            alert("Session expired or unauthorized! Redirecting to login.");
            localStorage.removeItem("token");
			localStorage.removeItem("email");
            window.location.href = "/user/login";
        }
    } catch (error) {
        console.error("Error fetching user data:", error);
        alert("Error loading dashboard. Please try again.");
    }

    // Logout button functionality
    document.getElementById("logoutBtn").addEventListener("click", function () {
        localStorage.removeItem("token"); // Clear token
        window.location.href = "/user/login"; // Redirect to login
    });
});
