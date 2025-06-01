document.addEventListener("DOMContentLoaded", function() {
	const signupForm = document.getElementById("signUpForm");
	const loginForm = document.getElementById("loginForm");

	if (signupForm) {
		signupForm.addEventListener("submit", async function(event) {
			event.preventDefault();

			const fullName = document.getElementById("fullName").value;
			const email = document.getElementById("email").value;
			const username = document.getElementById("username").value;
			const password = document.getElementById("password").value;
			const confirmPassword = document.getElementById("confirm-password").value;

			if (password !== confirmPassword) {
				alert("Passwords do not match!");
				return;
			}

			const response = await fetch("/user/auth/signup", {
				method: "POST",
				headers: { "Content-Type": "application/json" },
				body: JSON.stringify({ fullName, email, username, password })
			});

			if (response.ok) {
				alert("Signup successful! Please log in.");
				window.location.href = "/user/login";
			} else {
				alert("Signup failed. Please try again.");
			}
		});
	}

	if (loginForm) {
		loginForm.addEventListener("submit", async function(event) {
			event.preventDefault();

			const email = document.getElementById("email").value;
			const password = document.getElementById("password").value;

			const response = await fetch("/user/auth/login", {
				method: "POST",
				headers: { "Content-Type": "application/json" },
				body: JSON.stringify({ email, password })
			});

			if (response.ok) {
				const data = await response.json();
				localStorage.setItem("token", data.token);
				localStorage.setItem("email", email);
				alert("Login successful!");
				setTimeout(() => {
					window.location.href = "/user/dashboard";
				}, 500);
				// window.location.href = "/dashboard"; // Redirect to dashboard
			} else {
				alert("Login failed. Check your credentials and try again.");
			}
		});
	}
});
