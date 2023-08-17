/******/ (() => { // webpackBootstrap
/******/ 	"use strict";
var __webpack_exports__ = {};
/*!********************************************************************************************************!*\
  !*** ../../../themes/metronic/html/demo2/src/js/custom/apps/user-management/users/view/update-role.js ***!
  \********************************************************************************************************/


// Class definition
var KTUsersUpdateRole = function () {
    // Shared variables
    const element = document.getElementById('kt_modal_update_role');
    const form = element.querySelector('#kt_modal_update_role_form');
    const modal = new bootstrap.Modal(element);
    var userEmail = "";
    var userId = "";
    var redirectUrl = "";
    document.addEventListener('DOMContentLoaded', function () {
        const emailLink = document.getElementById('emailLink');
        userEmail = emailLink.textContent.trim();
        const userIDLink = document.getElementById('userId');
        userId = userIDLink.textContent.trim();

        // console.log('Email:', email);
        // You can use the email value in your JavaScript code
    });

    // Init add schedule modal
    var initUpdateRole = () => {

        // Close button handler
        const closeButton = element.querySelector('[data-kt-users-modal-action="close"]');
        closeButton.addEventListener('click', e => {
            e.preventDefault();

            Swal.fire({
                text: "Are you sure you would like to cancel?",
                icon: "warning",
                showCancelButton: true,
                buttonsStyling: false,
                confirmButtonText: "Yes, cancel it!",
                cancelButtonText: "No, return",
                customClass: {
                    confirmButton: "btn btn-primary",
                    cancelButton: "btn btn-active-light"
                }
            }).then(function (result) {
                if (result.value) {
                    form.reset(); // Reset form	
                    modal.hide(); // Hide modal				
                } else if (result.dismiss === 'cancel') {
                    Swal.fire({
                        text: "Your form has not been cancelled!.",
                        icon: "error",
                        buttonsStyling: false,
                        confirmButtonText: "Ok, got it!",
                        customClass: {
                            confirmButton: "btn btn-primary",
                        }
                    });
                }
            });
        });

        // Cancel button handler
        const cancelButton = element.querySelector('[data-kt-users-modal-action="cancel"]');
        cancelButton.addEventListener('click', e => {
            e.preventDefault();

            Swal.fire({
                text: "Are you sure you would like to cancel?",
                icon: "warning",
                showCancelButton: true,
                buttonsStyling: false,
                confirmButtonText: "Yes, cancel it!",
                cancelButtonText: "No, return",
                customClass: {
                    confirmButton: "btn btn-primary",
                    cancelButton: "btn btn-active-light"
                }
            }).then(function (result) {
                if (result.value) {
                    form.reset(); // Reset form	
                    modal.hide(); // Hide modal				
                } else if (result.dismiss === 'cancel') {
                    Swal.fire({
                        text: "Your form has not been cancelled!.",
                        icon: "error",
                        buttonsStyling: false,
                        confirmButtonText: "Ok, got it!",
                        customClass: {
                            confirmButton: "btn btn-primary",
                        }
                    });
                }
            });
        });

        // Submit button handler
        const submitButton = element.querySelector('[data-kt-users-modal-action="submit"]');
        submitButton.addEventListener('click', function (e) {
            // Prevent default button action
            e.preventDefault();

            // Show loading indication
            submitButton.setAttribute('data-kt-indicator', 'on');

            // Disable button to avoid multiple click 
            submitButton.disabled = true;
            const radioInput = document.querySelector('#kt_modal_update_role_option_0');
            var role = 0;
            if (radioInput.checked) {
                role = 1;
            }

            var formData = {
                role: role, 
                email: userEmail
            }
            fetch("/update", {
                method: "PUT",
                headers: {
                    "Content-Type": "application/json"
                },
                body: JSON.stringify(formData)
            })
            .then(response => response.text())
            .then(data => {
                if (data.startsWith("redirect:")) {
                    // Extract the URL from the response and perform the redirection
                    redirectUrl = data.substring("redirect:".length);
                    console.log("redirectUrl: " + redirectUrl);
                } else {
                    console.log("data: " + data);
                    textMessage = data;
                    iconMessage = "error";
                }
                setTimeout(function () {
                    // Remove loading indication
                    submitButton.removeAttribute('data-kt-indicator');
    
                    // Enable button
                    submitButton.disabled = false;
    
                    // Show popup confirmation 
                    Swal.fire({
                        text: "Form has been successfully submitted!",
                        icon: "success",
                        buttonsStyling: false,
                        confirmButtonText: "Ok, got it!",
                        customClass: {
                            confirmButton: "btn btn-primary"
                        }
                    }).then(function (result) {
                        if (result.isConfirmed) {
                            modal.hide();
                            window.location.href = "/admin/users/view/" + userId;
                        }
                    });
    
                    // form.submit(); // Submit form
                }, 2000);
            })
            // Simulate form submission. For more info check the plugin's official documentation: https://sweetalert2.github.io/
            
        });
    }

    return {
        // Public functions
        init: function () {
            initUpdateRole();
        }
    };
}();

// On document ready
KTUtil.onDOMContentLoaded(function () {
    KTUsersUpdateRole.init();
});
/******/ })()
;
//# sourceMappingURL=update-role.js.map