/******/ (() => { // webpackBootstrap
/******/ 	"use strict";
    var __webpack_exports__ = {};
    /*!********************************************************************************************!*\
      !*** ../../../themes/metronic/html/demo2/src/js/custom/account/settings/signin-methods.js ***!
      \********************************************************************************************/


    // Class definition
    var KTAccountSettingsSigninMethods = function () {
        // Private functions
        var iconMessage = "success";
        var textMessage = "Thank you! You've updated your sign-in methods";
        var initSettings = function () {

            // UI elements
            var signInMainEl = document.getElementById('kt_signin_email');
            var signInEditEl = document.getElementById('kt_signin_email_edit');
            var passwordMainEl = document.getElementById('kt_signin_password');
            var passwordEditEl = document.getElementById('kt_signin_password_edit');

            // button elements
            var signInChangeEmail = document.getElementById('kt_signin_email_button');
            var signInCancelEmail = document.getElementById('kt_signin_cancel');
            var passwordChange = document.getElementById('kt_signin_password_button');
            var passwordCancel = document.getElementById('kt_password_cancel');

            // toggle UI
            signInChangeEmail.querySelector('button').addEventListener('click', function () {
                toggleChangeEmail();
            });

            signInCancelEmail.addEventListener('click', function () {
                toggleChangeEmail();
            });

            passwordChange.querySelector('button').addEventListener('click', function () {
                toggleChangePassword();
            });

            passwordCancel.addEventListener('click', function () {
                toggleChangePassword();
            });

            var toggleChangeEmail = function () {
                signInMainEl.classList.toggle('d-none');
                signInChangeEmail.classList.toggle('d-none');
                signInEditEl.classList.toggle('d-none');
            }

            var toggleChangePassword = function () {
                passwordMainEl.classList.toggle('d-none');
                passwordChange.classList.toggle('d-none');
                passwordEditEl.classList.toggle('d-none');
            }
        }
        var userEmail = "";
        document.addEventListener('DOMContentLoaded', function () {
            const emailLink = document.getElementById('emailLink');
            userEmail = emailLink.textContent.trim();

            console.log('Email:', email);
            // You can use the email value in your JavaScript code
        });
        var handleChangeEmail = function (e) {
            var validation;

            // form elements
            var signInForm = document.getElementById('kt_signin_change_email');

            validation = FormValidation.formValidation(
                signInForm,
                {
                    fields: {
                        emailaddress: {
                            validators: {
                                notEmpty: {
                                    message: 'Email is required'
                                },
                                emailAddress: {
                                    message: 'The value is not a valid email address'
                                }
                            }
                        },

                        confirmemailpassword: {
                            validators: {
                                notEmpty: {
                                    message: 'Password is required'
                                }
                            }
                        }
                    },

                    plugins: { //Learn more: https://formvalidation.io/guide/plugins
                        trigger: new FormValidation.plugins.Trigger(),
                        bootstrap: new FormValidation.plugins.Bootstrap5({
                            rowSelector: '.fv-row'
                        })
                    }
                }
            );

            signInForm.querySelector('#kt_signin_submit').addEventListener('click', function (e) {
                e.preventDefault();
                console.log('click');

                validation.validate().then(function (status) {
                    if (status == 'Valid') {
                        
                        var formData = {
                            newEmail: $("input[name=emailaddress]").val(),
                            email: userEmail
                        };
                        var redirectUrl = "";

                        fetch("/updateEmail", {
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
                                    // Hide loading indication
                                    // submitButton.removeAttribute('data-kt-indicator');

                                    // Enable button
                                    // submitButton.disabled = false;

                                    swal.fire({
                                        text: textMessage,
                                        icon: iconMessage,
                                        buttonsStyling: false,
                                        confirmButtonText: "Ok, got it!",
                                        customClass: {
                                            confirmButton: "btn fw-bold btn-light-primary"
                                        }
                                    }).then(function (result) {
                                        if (result.isConfirmed) {

                                            // form.querySelector('[name="email"]').value= "";
                                            // form.querySelector('[name="password"]').value= "";
                                            // form.reset();
                                            //form.submit(); // submit form
                                            if (iconMessage == "success") {
                                                window.location.href = redirectUrl;
                                            }
                                        }

                                    });
                                }, 2000);
                            })
                    } else {
                        swal.fire({
                            text: "Sorry, looks like there are some errors detected, please try again.",
                            icon: "error",
                            buttonsStyling: false,
                            confirmButtonText: "Ok, got it!",
                            customClass: {
                                confirmButton: "btn font-weight-bold btn-light-primary"
                            }
                        });
                    }
                });
            });
        }

        var handleChangePassword = function (e) {
            var validation;

            // form elements
            var passwordForm = document.getElementById('kt_signin_change_password');

            validation = FormValidation.formValidation(
                passwordForm,
                {
                    fields: {
                        currentpassword: {
                            validators: {
                                notEmpty: {
                                    message: 'Current Password is required'
                                }
                            }
                        },

                        newpassword: {
                            validators: {
                                notEmpty: {
                                    message: 'New Password is required'
                                }
                            }
                        },

                        confirmpassword: {
                            validators: {
                                notEmpty: {
                                    message: 'Confirm Password is required'
                                },
                                identical: {
                                    compare: function () {
                                        return passwordForm.querySelector('[name="newpassword"]').value;
                                    },
                                    message: 'The password and its confirm are not the same'
                                }
                            }
                        },
                    },

                    plugins: { //Learn more: https://formvalidation.io/guide/plugins
                        trigger: new FormValidation.plugins.Trigger(),
                        bootstrap: new FormValidation.plugins.Bootstrap5({
                            rowSelector: '.fv-row'
                        })
                    }
                }
            );

            passwordForm.querySelector('#kt_password_submit').addEventListener('click', function (e) {
                e.preventDefault();
                console.log('click');

                validation.validate().then(function (status) {
                    if (status == 'Valid') {
                        var formdata = {
                            currentPassword: $("input[name=currentpassword]").val(),
                            password: $("input[name=newpassword]").val(),
                            confirmPassword: $("input[name=confirmpassword]").val(),
                            email: userEmail

                        };
                        var redirectUrl = "";
                        fetch("/updatePassword", {
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
                                    } else {
                                        console.log("data: " + data);
                                        textMessage = data;
                                        iconMessage = "error";
                                    }
                                    setTimeout(function () {
                                        // Hide loading indication
                                        // submitButton.removeAttribute('data-kt-indicator');

                                        // Enable button
                                        // submitButton.disabled = false;

                                        // Show message popup. For more info check the plugin's official documentation: https://sweetalert2.github.io/
                                        swal.fire({
                                            text: textMessage,
                                            icon: iconMessage,
                                            buttonsStyling: false,
                                            confirmButtonText: "Ok, got it!",
                                            customClass: {
                                                confirmButton: "btn font-weight-bold btn-light-primary"
                                            }
                                        }).then(function (result) {
                                            if (result.isConfirmed) {

                                                // form.querySelector('[name="email"]').value= "";
                                                // form.querySelector('[name="password"]').value= "";
                                                // form.reset();
                                                //form.submit(); // submit form
                                                if (iconMessage == "success") {
                                                    window.location.href = redirectUrl;
                                                }
                                            }
                                        });

                                    }, 1500);
                                }));

                    } else {
                        swal.fire({
                            text: "Sorry, looks like there are some errors detected, please try again.",
                            icon: "error",
                            buttonsStyling: false,
                            confirmButtonText: "Ok, got it!",
                            customClass: {
                                confirmButton: "btn font-weight-bold btn-light-primary"
                            }
                        });
                    }
                });
            });
        }

        // Public methods
        return {
            init: function () {
                initSettings();
                handleChangeEmail();
                handleChangePassword();
            }
        }
    }();

    // On document ready
    KTUtil.onDOMContentLoaded(function () {
        KTAccountSettingsSigninMethods.init();
    });

    /******/
})()
    ;
//# sourceMappingURL=signin-methods.js.map