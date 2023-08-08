/******/ (() => { // webpackBootstrap
/******/ 	"use strict";
var __webpack_exports__ = {};
/*!*********************************************************************************************************!*\
  !*** ../../../themes/metronic/html/demo2/src/js/custom/authentication/password-reset/password-reset.js ***!
  \*********************************************************************************************************/


// Class Definition
var KTPasswordResetGeneral = function() {
    // Elements
    var form;
    var submitButton;
	var validator;
    var iconMessage = "success";
    var textMessage ="send otp successfully";
    var waitTime;

    var handleForm = function(e) {
        // Init form validation rules. For more info check the FormValidation plugin's official documentation:https://formvalidation.io/
        validator = FormValidation.formValidation(
			form,
			{
				fields: {					
					'email': {
                        validators: {
							notEmpty: {
								message: 'Email address is required'
							},
                            emailAddress: {
								message: 'The value is not a valid email address'
							}
						}
					} 
				},
				plugins: {
					trigger: new FormValidation.plugins.Trigger(),
					bootstrap: new FormValidation.plugins.Bootstrap5({
                        rowSelector: '.fv-row',
						eleInvalidClass: '',
                        eleValidClass: ''
                    })
				}
			}
		);		

        submitButton.addEventListener('click', function (e) {
            e.preventDefault();

            // Validate form
            validator.validate().then(function (status) {
                if (status == 'Valid') {
                    // Show loading indication
                    submitButton.setAttribute('data-kt-indicator', 'on');

                    // Disable button to avoid multiple click 
                    submitButton.disabled = true;

                    var formdata = {
                        email: $("input[name=email]").val(),
                    };

                    var redirectUrl = "";

                    fetch("/send-otp", {
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

                    // Simulate ajax request
                    setTimeout(function() {
                        // Hide loading indication
                        submitButton.removeAttribute('data-kt-indicator');

                        // Enable button
                        submitButton.disabled = false;

                        // Show message popup. For more info check the plugin's official documentation: https://sweetalert2.github.io/
                        Swal.fire({
                            text: textMessage,
                            icon: iconMessage,
                            buttonsStyling: false,
                            confirmButtonText: "Ok, got it!",
                            customClass: {
                                confirmButton: "btn btn-primary"
                            }
                        }).then(function (result) {
                            if (result.isConfirmed) { 
                                form.querySelector('[name="email"]').value= "";                          
                                //form.submit();
                                form.reset(); // reset form
                                if(iconMessage == "success") {
                                    window.location.href = redirectUrl;}
                                
                            }
                        });
                    }, 1500);   						
                } else {
                    // Show error popup. For more info check the plugin's official documentation: https://sweetalert2.github.io/
                    Swal.fire({
                        text: "Sorry, looks like there are some errors detected, please try again.",
                        icon: "error",
                        buttonsStyling: false,
                        confirmButtonText: "Ok, got it!",
                        customClass: {
                            confirmButton: "btn btn-primary"
                        }
                    });
                }
            });  
		});
    }

    // Public Functions
    return {
        // public functions
        init: function() {
            form = document.querySelector('#kt_password_reset_form');
            submitButton = document.querySelector('#kt_password_reset_submit');

            handleForm();
        }
    };
}();

// On document ready
KTUtil.onDOMContentLoaded(function() {
    KTPasswordResetGeneral.init();
});

/******/ })()
;
//# sourceMappingURL=password-reset.js.map