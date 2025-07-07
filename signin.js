// Tab switching functionality
        const tabs = document.querySelectorAll('.auth-tab');
        const forms = document.querySelectorAll('.form-content');

        tabs.forEach(tab => {
            tab.addEventListener('click', () => {
                // Remove active class from all tabs and forms
                tabs.forEach(t => t.classList.remove('active'));
                forms.forEach(f => f.classList.remove('active'));
                
                // Add active class to clicked tab
                tab.classList.add('active');
                
                // Show corresponding form
                const targetForm = document.getElementById(tab.dataset.tab + '-form');
                if (targetForm) {
                    targetForm.classList.add('active');
                }
            });
        });

        // Form submission handling
        document.getElementById('signin-form').addEventListener('submit', (e) => {
            e.preventDefault();
            const email = document.getElementById('signin-email').value;
            const password = document.getElementById('signin-password').value;
            
            if (email && password) {
                alert('Sign in functionality would be implemented here');
            }
        });

        document.getElementById('signup-form').addEventListener('submit', (e) => {
            e.preventDefault();
            const username = document.getElementById('signup-username').value;
            const email = document.getElementById('signup-email').value;
            const password = document.getElementById('signup-password').value;
            const termsAccepted = document.getElementById('terms').checked;
            
            if (username && email && password && termsAccepted) {
                alert('Registration functionality would be implemented here');
            } else if (!termsAccepted) {
                alert('Please accept the terms and conditions');
            }
        });

        // Enhanced input interactions
        const inputs = document.querySelectorAll('input[type="text"], input[type="email"], input[type="password"]');
        
        inputs.forEach(input => {
            input.addEventListener('focus', () => {
                input.parentElement.style.transform = 'scale(1.02)';
            });
            
            input.addEventListener('blur', () => {
                input.parentElement.style.transform = 'scale(1)';
            });
        });

        // Smooth animations on load
        window.addEventListener('load', () => {
            document.querySelector('.glass-container').style.animation = 'slideIn 0.8s ease-out';
        });

        // Add CSS animation keyframes
        const style = document.createElement('style');
        style.textContent = `
            @keyframes slideIn {
                from {
                    opacity: 0;
                    transform: translateX(100px);
                }
                to {
                    opacity: 1;
                    transform: translateX(0);
                }
            }
        `;
        document.head.appendChild(style);