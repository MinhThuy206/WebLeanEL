/******/ (() => { // webpackBootstrap
/******/ 	"use strict";
var __webpack_exports__ = {};
/*!*********************************************************************************************!*\
  !*** ../../../themes/metronic/html/demo2/src/js/custom/authentication/sign-in/two-steps.js ***!
  \*********************************************************************************************/


// Class Definition
var KTSigninTwoSteps = function() {
    // Elements
    var form;
    var submitButton;
    var iconMessage = "success";
    var textMessage ="send otp successfully";
    var waitTime;
    // Handle form
    var handleForm = function(e) {        
        // Handle form submit
        submitButton.addEventListener('click', function (e) {
            e.preventDefault();

            var validated = true;

            var inputs = [].slice.call(form.querySelectorAll('input[maxlength="1"]'));
            inputs.map(function (input) {
                if (input.value === '' || input.value.length === 0) {
                    validated = false;
                }
            });

            if (validated === true) {
                // Show loading indication
                submitButton.setAttribute('data-kt-indicator', 'on');

                var formdata = {
                    otp: $("input[name=otp]").val(),
                    email: $("input[name=email]").val()
                };

                var redirectUrl = "";

                    fetch("/verify-otp", {
                        method: "POST",
                        headers: {
                            "Content-Type": "application/json"
                        },
                        body: JSON.stringify(formdata)
                    })
                    .then(response => response.text()
                    .then(data => {
                        if (data.startsWith("redirect:")) {
                            // Extract the URL from the response and perform the redirection
                            redirectUrl = data.substring("redirect:".length);
                            console.log("redirectUrl: " + redirectUrl);
                            waitTime = 5000;
                        } else {
                            console.log("data: " + data);
                            textMessage = data;
                            iconMessage = "error";
                            waitTime = 1500;
                        }
                    }))

                // Disable button to avoid multiple click 
                submitButton.disabled = true;

                // Simulate ajax request
                setTimeout(function() {
                    // Hide loading indication
                    submitButton.removeAttribute('data-kt-indicator');

                    // Enable button
                    submitButton.disabled = false;

                    // Show message popup. For more info check the plugin's official documentation: https://sweetalert2.github.io/
                    Swal.fire({
                        text: "You have been successfully verified!",
                        icon: "success",
                        buttonsStyling: false,
                        confirmButtonText: "Ok, got it!",
                        customClass: {
                            confirmButton: "btn btn-primary"
                        }
                    }).then(function (result) {
                        if (result.isConfirmed) { 
                            inputs.map(function (input) {
                                input.value = '';
                            });
                        }
                    });
                }, 1000); 
            } else {
                swal.fire({
                    text: "Please enter valid securtiy code and try again.",
                    icon: "error",
                    buttonsStyling: false,
                    confirmButtonText: "Ok, got it!",
                    customClass: {
                        confirmButton: "btn fw-bold btn-light-primary"
                    }
                }).then(function() {
                    KTUtil.scrollTop();
                });
            }
        });
    }

    // Public functions
    return {
        // Initialization
        init: function() {
            form = document.querySelector('#kt_sing_in_two_steps_form');
            submitButton = document.querySelector('#kt_sing_in_two_steps_submit');

            handleForm();
        }
    };
}();

// On document ready
KTUtil.onDOMContentLoaded(function() {
    KTSigninTwoSteps.init();
});
/******/ })()
;
//# sourceMappingURL=two-steps.js.map