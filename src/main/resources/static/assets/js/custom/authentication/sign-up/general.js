/******/ (() => { // webpackBootstrap
/******/ 	"use strict";
var __webpack_exports__ = {};
/*!*******************************************************************************************!*\
  !*** ../../../themes/metronic/html/demo2/src/js/custom/authentication/sign-up/general.js ***!
  \*******************************************************************************************/


// Class definition
var KTSignupGeneral = function() {
    // Elements
    var form;
    var submitButton;
    var validator;
    var passwordMeter;
    var iconMessage = "success";
    var textMessage ="You have successfully registered!";
    var waitTime;

    function isValidPhoneNumber(phoneNumber) {
  // Biểu thức chính quy để kiểm tra định dạng số điện thoại
      var phoneRegex = /^[0-9]{10}$/;

  // Kiểm tra giá trị với biểu thức chính quy
      return phoneRegex.test(phoneNumber);
  }

    // Handle form
    var handleForm  = function(e) {
        // Init form validation rules. For more info check the FormValidation plugin's official documentation:https://formvalidation.io/
        validator = FormValidation.formValidation(
			form,
			{
				fields: {
					'fullname': {
						validators: {
							notEmpty: {
								message: 'Full Name is required'
							}
						}
                    },
                    'username': {
						validators: {
							notEmpty: {
								message: 'User Name is required'
							}
						}
					},
                    'address': {
                        validators: {
                            notEmpty: {
                                message: 'Address is required'
                            }
                        }
                    },
					'email': {
                        validators: {
							notEmpty: {
								message: 'Email address is required'
							},
                            emailAddress: {
								message: 'The value is not a valid email address'
							}
						}
					},
                    'phone': {
                        validators: {
                            notEmpty: {
                                message: 'Phone number is required'
                            },
                            callback: {
                            message: 'Please enter a valid phone number',
                            callback: function(input) {
                                if (input.value.length > 0) {
                                    return isValidPhoneNumber(input.value);
                                }
                            }
                        }
                        }
                    },
                    'password': {
                        validators: {
                            notEmpty: {
                                message: 'The password is required'
                            },
                            callback: {
                                message: 'Please enter valid password',
                                callback: function(input) {
                                    if (input.value.length > 0) {
                                        return validatePassword();
                                    }
                                }
                            }
                        }
                    },
                    'confirm-password': {
                        validators: {
                            notEmpty: {
                                message: 'The password confirmation is required'
                            },
                            identical: {
                                compare: function() {
                                    return form.querySelector('[name="password"]').value;
                                },
                                message: 'The password and its confirm are not the same'
                            }
                        }
                    },
                    'toc': {
                        validators: {
                            notEmpty: {
                                message: 'You must accept the terms and conditions'
                            }
                        }
                    }
				},
				plugins: {
					trigger: new FormValidation.plugins.Trigger({
                        event: {
                            password: false
                        }  
                    }),
					bootstrap: new FormValidation.plugins.Bootstrap5({
                        rowSelector: '.fv-row',
                        eleInvalidClass: '',
                        eleValidClass: ''
                    })
				}
			}
		);

        // Handle form submit
        submitButton.addEventListener('click', function (e) {
            e.preventDefault();

            validator.revalidateField('password');

            validator.validate().then(function(status) {
		        if (status == 'Valid') {
                    // Show loading indication
                    submitButton.setAttribute('data-kt-indicator', 'on');

                    // Disable button to avoid multiple click 
                    submitButton.disabled = true;

                    var formData = {
                        fullname: $("input[name=fullname]").val(),
                        username: $("input[name=username]").val(),
                        email: $("input[name=email]").val(),
                        password: $("input[name=password]").val(),
                        phone: $("input[name=phone]").val(),
                        address: $("input[name=address]").val(),
                        confirmPassword: $("input[name=confirm-password]").val(),
                        toc: $("input[name=toc]").is(":checked"),
                    };
                    var redirectUrl = "";

                    fetch("/submitForm", {
                        method: "POST",
                        headers: {
                            "Content-Type": "application/json"
                        },
                        body: JSON.stringify(formData)
                    })
                    .then(response => response.text()
                    )
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
                                    form.reset();  // reset form                    
                                    passwordMeter.reset();  // reset password meter
                                    
                                    
                                    //form.submit();
                                    // window.location.href = '/user/login';
                                    if(iconMessage == "success") {
                                        window.location.href = redirectUrl;
                                    } else {
                                        textMessage ="You have successfully registered!";
                                        iconMessage = "success";
                                    }
    
                                }
                            });
                        }, 1500);
                    }) 						
                    
                    // Simulate ajax request
                      
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

        // Handle password input
        form.querySelector('input[name="password"]').addEventListener('input', function() {
            if (this.value.length > 0) {
                validator.updateFieldStatus('password', 'NotValidated');
            }
        });
    }

    // Password input validation
    var validatePassword = function() {
        return  (passwordMeter.getScore() === 100);
    }

    // Public functions
    return {
        // Initialization
        init: function() {
            // Elements
            form = document.querySelector('#kt_sign_up_form');
            submitButton = document.querySelector('#kt_sign_up_submit');
            passwordMeter = KTPasswordMeter.getInstance(form.querySelector('[data-kt-password-meter="true"]'));

            handleForm ();
        }
    };
}();

// On document ready
KTUtil.onDOMContentLoaded(function() {
    KTSignupGeneral.init();
});

/******/ })()
;
//# sourceMappingURL=general.js.map