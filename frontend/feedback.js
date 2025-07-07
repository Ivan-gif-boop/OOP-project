//Star rating functionality
        const starRatings = document.querySelectorAll('.star-rating');
        const ratings = {};

        starRatings.forEach(rating => {
            const stars = rating.querySelectorAll('.star');
            const ratingName = rating.getAttribute('data-rating');
            
            stars.forEach((star, index) => {
                star.addEventListener('click', () => {
                    const value = parseInt(star.getAttribute('data-value'));
                    ratings[ratingName] = value;
                    
                    // Update visual state
                    stars.forEach((s, i) => {
                        if (i < value) {
                            s.classList.add('active');
                        } else {
                            s.classList.remove('active');
                        }
                    });
                });
                
                // Hover effects
                star.addEventListener('mouseenter', () => {
                    const value = parseInt(star.getAttribute('data-value'));
                    stars.forEach((s, i) => {
                        if (i < value) {
                            s.style.color = '#990000';
                            s.style.transform = 'scale(1.1)';
                        } else {
                            s.style.color = '#666';
                            s.style.transform = 'scale(1)';
                        }
                    });
                });
                
                rating.addEventListener('mouseleave', () => {
                    stars.forEach((s, i) => {
                        const currentRating = ratings[ratingName] || 0;
                        if (i < currentRating) {
                            s.style.color = '#990000';
                            s.style.transform = 'scale(1.1)';
                        } else {
                            s.style.color = '#666';
                            s.style.transform = 'scale(1)';
                        }
                    });
                });
            });
        });

        // Form submission
        document.getElementById('feedbackForm').addEventListener('submit', (e) => {
            e.preventDefault();
            
            const formData = new FormData(e.target);
            const data = Object.fromEntries(formData);
            data.ratings = ratings;
            
            alert('Thank you for your feedback! We appreciate your input.');
            console.log('Feedback submitted:', data);
            
            // Reset form
            e.target.reset();
            starRatings.forEach(rating => {
                rating.querySelectorAll('.star').forEach(star => {
                    star.classList.remove('active');
                    star.style.color = '#666';
                    star.style.transform = 'scale(1)';
                });
            });
            
            // Clear ratings object
            Object.keys(ratings).forEach(key => delete ratings[key]);
        });