/******/ (() => { // webpackBootstrap
/******/ 	"use strict";
    var __webpack_exports__ = {};
    /*!*********************************************************************************************!*\
      !*** ../../../themes/metronic/html/demo2/src/js/custom/account/settings/profile-details.js ***!
      \*********************************************************************************************/


    // Class definition
    var KTAccountSettingsProfileDetails = function () {
        // Private variables
        var form;
        var submitButton;
        var validation;
        var iconMessage = "success";
        var textMessage = "Thank you! You've updated your basic info";

        // Private functions
        var initValidation = function () {
            // Init form validation rules. For more info check the FormValidation plugin's official documentation:https://formvalidation.io/
            validation = FormValidation.formValidation(
                form,
                {
                    fields: {
                        fullname: {
                            validators: {
                                notEmpty: {
                                    message: 'Full Name  is required'
                                }
                            }
                        },
                        phone: {
                            validators: {
                                notEmpty: {
                                    message: 'Contact phone number is required'
                                }
                            }
                        },
                        address: {
                            validators: {
                                notEmpty: {
                                    message: 'Address is required'
                                }
                            }
                        },
                    },
                    plugins: {
                        trigger: new FormValidation.plugins.Trigger(),
                        submitButton: new FormValidation.plugins.SubmitButton(),
                        //defaultSubmit: new FormValidation.plugins.DefaultSubmit(), // Uncomment this line to enable normal button submit after form validation
                        bootstrap: new FormValidation.plugins.Bootstrap5({
                            rowSelector: '.fv-row',
                            eleInvalidClass: '',
                            eleValidClass: ''
                        })
                    }
                }
            );
        }
        var userEmail = "";
        document.addEventListener('DOMContentLoaded', function () {
            const emailLink = document.getElementById('emailLink');
            userEmail = emailLink.textContent.trim();

            console.log('Email:', email);
            // You can use the email value in your JavaScript code
        });
        var handleForm = function () {
            submitButton.addEventListener('click', function (e) {
                e.preventDefault();

                validation.validate().then(function (status) {
                    if (status == 'Valid') {
                        submitButton.setAttribute('data-kt-indicator', 'on');

                        // Disable button to avoid multiple click
                        submitButton.disabled = true;






                        var formData = {
                            fullname: $("input[name=fullname]").val(),
                            mobile: $("input[name=phone]").val(),
                            address: $("input[name=address]").val(),
                            email: userEmail
                        };
                        var userId = /*[[${user.id}]]*/ 'default-value-if-user.id-is-null';
                        var redirectUrl = "";

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
                                    // Hide loading indication
                                    submitButton.removeAttribute('data-kt-indicator');

                                    // Enable button
                                    submitButton.disabled = false;

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
                                            form.reset();
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
                                confirmButton: "btn fw-bold btn-light-primary"
                            }
                        });
                    }
                });
            });
        }

        // Public methods
        return {
            init: function () {
                form = document.getElementById('kt_account_profile_details_form');
                submitButton = form.querySelector('#kt_account_profile_details_submit');
                handleForm();
                initValidation();
            }
        }
    }();

    // On document ready
    KTUtil.onDOMContentLoaded(function () {
        KTAccountSettingsProfileDetails.init();
    });

    /******/
})()

//# sourceMappingURL=profile-details.js.map