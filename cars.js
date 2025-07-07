document.addEventListener('DOMContentLoaded', function() {
            const showMoreBtn = document.getElementById('showMoreBtn');
            const additionalCars = document.querySelectorAll('.additional-car');
            let isExpanded = false;

            showMoreBtn.addEventListener('click', function() {
                if (!isExpanded) {
                    // Show additional cars
                    additionalCars.forEach((car, index) => {
                        setTimeout(() => {
                            car.classList.remove('hidden');
                            car.classList.add('fade-in');
                        }, index * 100);
                    });
                    
                    showMoreBtn.textContent = 'Show Less';
                    isExpanded = true;
                } else {
                    // Hide additional cars
                    additionalCars.forEach(car => {
                        car.classList.add('hidden');
                        car.classList.remove('fade-in');
                    });
                    
                    showMoreBtn.textContent = 'Show More';
                    isExpanded = false;
                }
            });

            // Add click event to all View Details buttons
            document.querySelectorAll('.view-details-btn').forEach(btn => {
                btn.addEventListener('click', function(e) {
                    e.preventDefault();
                    
                    // Create ripple effect
                    const ripple = document.createElement('span');
                    ripple.classList.add('ripple');
                    this.appendChild(ripple);
                    
                    // Remove ripple after animation
                    setTimeout(() => {
                        ripple.remove();
                    }, 600);
                    
                    // Show alert (replace with actual functionality)
                    const carTitle = this.closest('.car-card').querySelector('.car-title').textContent;
                    setTimeout(() => {
                        alert(`Viewing details for ${carTitle}`);
                    }, 200);
                });
            });

            // Add some dynamic effects
            const cards = document.querySelectorAll('.car-card');
            cards.forEach(card => {
                card.addEventListener('mouseenter', function() {
                    this.style.transform = 'translateY(-10px) scale(1.02)';
                });
                
                card.addEventListener('mouseleave', function() {
                    this.style.transform = 'translateY(0) scale(1)';
                });
            });
        });

        // Add CSS for ripple effect
        const style = document.createElement('style');
        style.textContent = `
            .ripple {
                position: absolute;
                border-radius: 50%;
                background: rgba(255, 255, 255, 0.3);
                transform: scale(0);
                animation: ripple 0.6s linear;
                pointer-events: none;
            }
            
            @keyframes ripple {
                to {
                    transform: scale(4);
                    opacity: 0;
                }
            }
        `;
        document.head.appendChild(style);