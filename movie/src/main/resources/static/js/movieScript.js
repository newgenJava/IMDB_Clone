async function fetchMovies() {
	try {
		var response = await fetch('http://localhost:8080/movie/api/movies');
		var movies = await response.json();
		const email = localStorage.getItem("email");

		var container = document.getElementById('movieList');
		container.innerHTML = '';

		movies.forEach(movie => {
			var movieDiv = document.createElement('div');
			movieDiv.className = 'movie';
			movieDiv.innerHTML = `
        <div class="movie-title">${movie.title} (${movie.year})</div>
        <div class="movie-meta"><strong>Genre:</strong> ${movie.genre} | <strong>Director:</strong> ${movie.director}</div>

        <div class="reviews">
          <h4>Reviews:</h4>
          ${movie.reviews && movie.reviews.length > 0
					? movie.reviews.map(review => `
                  <div class="review">
                    <div class="reviewer">
					<button class="transparent-btn" onclick="window.location.href='http://localhost:8080/user/userInfo?name=${encodeURIComponent(review.reviewer)}'" >
					 ${review.reviewer} </button>
					<span class="rating">(${review.rating}/5)</span></div>
                    <div class="comment">${review.comment}</div>
                  </div>
                `).join('')
					: '<p class="no-reviews">No reviews yet.</p>'
				}
        </div>

        <button class="add-review-btn" onclick="toggleForm(${movie.id})">+ Add Review</button>

        <div class="review-form" id="form-${movie.id}">
          <input type="text" id="name-${movie.id}" value=${email} required>
          <input type="number" id="rating-${movie.id}" min="1" max="5" placeholder="Rating (1-5)" required>
          <textarea id="comment-${movie.id}" placeholder="Your review" rows="3" required></textarea>
          <button onclick="submitReview(${movie.id})">Submit</button>
        </div>
      `;

			container.appendChild(movieDiv);
		});
	} catch (error) {
		console.error('Error fetching movies:', error);
		document.getElementById('movieList').innerHTML = '<p>Error loading movies.</p>';
	}
}

function toggleForm(movieId) {
	var form = document.getElementById(`form-${movieId}`);
	form.style.display = form.style.display === 'block' ? 'none' : 'block';
}

async function submitReview(movieId) {
	var name = document.getElementById(`name-${movieId}`).value;
	var rating = parseInt(document.getElementById(`rating-${movieId}`).value);
	var comment = document.getElementById(`comment-${movieId}`).value;

	var review = { reviewer: name, rating, comment };

	try {
		var response = await fetch(`http://localhost:8080/movie/api/movies/${movieId}/reviews`, {
			method: 'POST',
			headers: { 'Content-Type': 'application/json' },
			body: JSON.stringify(review)
		});

		if (response.ok) {
			alert('Review added!');
			await fetchMovies(); // reload updated list
		} else {
			alert('Failed to add review.');
		}
	} catch (error) {
		console.error('Error submitting review:', error);
		alert('Error submitting review.');
	}
}
function toggleMovieForm() {
	var form = document.getElementById("movieForm");
	form.style.display = form.style.display === "block" ? "none" : "block";
}

async function submitMovie() {
	var title = document.getElementById("movieTitle").value;
	var genre = document.getElementById("movieGenre").value;
	var director = document.getElementById("movieDirector").value;
	var year = parseInt(document.getElementById("movieYear").value);

	var movie = { title, genre, director, year };

	try {
		var response = await fetch("http://localhost:8080/movie/api/movies", {
			method: "POST",
			headers: { "Content-Type": "application/json" },
			body: JSON.stringify(movie),
		});

		if (response.ok) {
			alert("Movie added!");
			document.getElementById("movieForm").reset(); // optional: clear fields
			toggleMovieForm(); // hide form
		} else {
			alert("Failed to add movie.");
		}
	} catch (error) {
		console.error("Error submitting movie:", error);
		alert("Error submitting movie." + error);
	}
}

window.onload = fetchMovies;
