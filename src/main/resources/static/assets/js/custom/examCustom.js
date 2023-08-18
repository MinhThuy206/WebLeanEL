$(function () {
    var countDownDate = new Date().getTime() + 30 * 60 * 1000; // Thời gian kết thúc đếm ngược là 30 phút sau thời điểm hiện tại
    var x = setInterval(function () {
        var now = new Date().getTime();
        var distance = countDownDate - now;
        var minutes = Math.floor((distance % (1000 * 60 * 60)) / (1000 * 60));
        var seconds = Math.floor((distance % (1000 * 60)) / 1000);
        $("#time").text("Time remaining: " + minutes + ":" + seconds);
        if (distance < 0) {
            $('#submit-btn').click();
        }
    }, 1000);

    let list_id = $('.question-id');
    list_id.each(function (index, l) {
        let parent = $(this).closest('div.Quiz-inner');
        let idDiv = "question-" + $(this).text().split(' ')[1];
        parent.attr('id', idDiv);

        let inputEle = $(`#question-${index + 1} input[type="radio"]`);
        let idEle = "question" + (index + 1);
        inputEle.each(function (index, l) {
            $(this).attr('name', idEle);
        });
    });
    $(`#question-1.Quiz-inner`).css("display", "block");

    // begin: Task 1
    let question_index_current = 1;
    const total_questions = $('.Quiz-inner').length;
    let task_selectedData = null;
    changeRemainingQuestions();
    $(".Quiz-confidence-buttons .btn.btn-default").addClass('disabled');

    $('#btn-next').on('click', function () {
        question_index_current++;
        console.log("qic: " + question_index_current);
        $(`#question-${question_index_current - 1}.Quiz-inner`).css("display", "none");
        $(`#question-${question_index_current}.Quiz-inner`).css("display", "block");
        ableButton(question_index_current);
        task_selectedData = null;
        confirmSubmit();
    });


    $('#btn-prev').on('click', function () {
        question_index_current--;
        console.log("qic: " + question_index_current);
        $(`#question-${question_index_current + 1}.Quiz-inner`).css("display", "none");
        $(`#question-${question_index_current}.Quiz-inner`).css("display", "block");
        ableButton(question_index_current);
        task_selectedData = null;
        confirmSubmit();
    });

    $(".radio input").on('click', function () {
        task_selectedData = $(this);
        $('.selecting-answer').removeClass('selecting-answer');
        ($(this)).addClass('selecting-answer');
        $(`#question-${question_index_current} .Quiz-confidence-buttons .btn`).removeClass('disabled');
        $(`#question-${question_index_current} .Quiz-confidence-buttons .selecting-btn`).removeClass('selecting-btn');
        resetValInput();
        changeRemainingQuestions();
        confirmSubmit();
    });


    $(".radio label").click(function () {
        task_selectedData = $(this);
        $('.selecting-answer').removeClass('selecting-answer');
        ($(this).prev()).addClass('selecting-answer');
        $(`#question-${question_index_current} .Quiz-confidence-buttons .btn`).removeClass('disabled');
        $(`#question-${question_index_current} .Quiz-confidence-buttons .selecting-btn`).removeClass('selecting-btn');
        resetValInput();
        changeRemainingQuestions();
        confirmSubmit();
    });

    function resetValInput() {
        list_input = $(`#question-${question_index_current} input`);
        list_input.each(function (index, l) {
            if (!$(this).hasClass('selecting-answer')) {
                $(this).val(index + 1);
            }
        });
    }

    function resetValDefault() {
        list_input = $(`#question-${question_index_current} input`);
        list_input.each(function (index, l) {
            $(this).val(index + 1);
        });
    }

    $(`.Quiz-confidence-buttons a`).click(function () {
        let data_topic = $(`#question-${question_index_current}`).attr('data-topic') + "_";
        let data_degree = "_" + $(this).attr('data-degree');
        resetValDefault();
        let data_answer = $(`#question-${question_index_current} .selecting-answer`).val();
        $(`#question-${question_index_current} .Quiz-confidence-buttons .selecting-btn`).removeClass('selecting-btn');
        $(this).addClass('selecting-btn');
        $(this).css('background-color', '#DEDEDE');
        $(`#question-${question_index_current} .selecting-answer`).val(data_topic + data_answer + data_degree);
        changeRemainingQuestions();
        confirmSubmit();
    });

    function ableButton(index) {
        if (index === 1) {
            $('#btn-prev').addClass('disabled');
        } else if (index === total_questions) {
            $('#btn-next').addClass('disabled');
        } else {
            $('#btn-prev').removeClass('disabled');
            $('#btn-next').removeClass('disabled');
        }
    }

    function confirmSubmit() {
        console.log("filled-all: " + $('.filled-all').length);
        if ($('.filled-all').length === total_questions) {
            setTimeout(function () {
                if (question_index_current == total_questions) {
                    var confirmSubmit = confirm('Bạn có muốn submit không?');
                    if (confirmSubmit) {
                        $('#submit-btn').click();
                        var url = "/exam/submit/" + userEmail;
                        // window.location.href = url
                    }
                }
            }, 1000);
        }
    }

    function filledAll(question_index_current) {
        let l = $(`#question-${question_index_current} .selecting-answer`).length;
        let h = $(`#question-${question_index_current} .Quiz-confidence-buttons a.btn.btn-default.selecting-btn`).length;
        // kiem tra so luong cau hoi con lai trong tung question
        console.log("l: " + l);
        console.log("h: " + h);
        if (l === 1 && h === 1) {
            $(`#question-${question_index_current}`).addClass('filled-all');
        } else {
            $(`#question-${question_index_current}`).removeClass('filled-all');
        }
        ableButton(question_index_current);
    }

    function changeRemainingQuestions() {
        filledAll(question_index_current);
        let l = $(`.Quiz-inner.filled-all`).length;
        let prg = Math.floor((l * 100) / total_questions);;
        $('.progress-bar').css("width", `${prg}%`);
    }


    $(".TigerQuiz .btn.btn-default").on('click', function () {
        $('.TigerQuiz .btn.btn-default').css('background-color', 'white');
        $('.TigerQuiz .btn.btn-default.selecting-btn').css('background-color', '#DEDEDE');
    });
    // end:Task scripts

    var userEmail = "";
    document.addEventListener('DOMContentLoaded', function () {
        const emailLink = document.getElementById('email_link');
        userEmail = emailLink.textContent.trim();

        console.log('Email:', userEmail);
        // You can use the email value in your JavaScript code
    });

    // $(document).ready(function () {
    //
    //     const UserAnswers = [];
    //     $(".Quiz-confidence-buttons a").on("click", function () {
    //         const selectedDegree = $(this).attr("data-degree"); // Lấy giá trị degree đã chọn
    //         const questionIndex = $(this)
    //             .closest(".Quiz-inner")
    //             .attr("id")
    //             .split("-")[1]; // Lấy chỉ số câu hỏi từ ID của phần tử
    //
    //         // Thêm giá trị câu trả lời và độ tự tin vào mảng userAnswers
    //         userAnswers.push({
    //             questionIndex: questionIndex,
    //             answer: $("input[name='question" + questionIndex + "']:checked").val(),
    //             confidence: selectedDegree
    //         });
    //     });
    //
    //     $("#submit-button"  ).on("click", function () {
    //         fetch("/exam/submit", {
    //             method: "POST",
    //             headers: {
    //                 "Content-Type": "application/json"
    //             },
    //             body: JSON.stringify(userAnswers)
    //         })
    //             .then(response => response.json())
    //             .then(data => {
    //                 // Chuyển hướng người dùng đến trang kết quả
    //                 window.location.href = "/exam/result"; // Đổi đường dẫn tới trang kết quả
    //             })
    //             .catch(error => {
    //                 console.error("Lỗi khi gửi dữ liệu: ", error);
    //             });
    //     });
    // });
    $("#saveButton").on("click", function () {
        // Lấy danh sách các phần tử radio đã được chọn
        const selectedAnswers = $("input[type='radio']:checked");

        // Tạo mảng lưu trữ câu trả lời người dùng
        const userAnswers = [];

        // Duyệt qua từng phần tử radio đã chọn
        selectedAnswers.each(function () {
            const questionId = $(this).data("question_id"); // Lấy ID của câu hỏi
            const selectedOption = $(this).val(); // Lấy giá trị của phần tử đã chọn

            // Thêm câu trả lời vào mảng userAnswers
            userAnswers.push({
                questionId: questionId,
                userAnswer: selectedOption
            });
        });


        saveUserAnswersToDatabase(userAnswers);
    });


    function saveUserAnswersToDatabase(userAnswers) {
        // const apiUrl = '/submit-answers';
        const apiUrl = '/exam/submit/' + userEmail;

        fetch(apiUrl, {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(userAnswers)
        })
            .then(response => {
                if (response.ok) {
                    console.log('User answers saved successfully.');
                } else {
                    console.error('Failed to save user answers.');
                }
            })
            .catch(error => {
                console.error('Error saving user answers:', error);
            });
    }
});


