$("#stringTimeFrom").datetimepicker({
    dayOfWeekStart: 1,
    format: 'Y-m-d H:i',
    formatTime: 'H:i',
    formatDate: 'Y-m-d',
    step: 30
});
$("#stringTimeTo").datetimepicker({
    dayOfWeekStart: 1,
    format: 'Y-m-d H:i',
    formatTime: 'H:i',
    formatDate: 'Y-m-d',
    step: 30
});

jQuery.datetimepicker.setLocale('ru');

function showAlert() {
    alert("The button was clicked!");
}