;
function findAndCheck(checkbox, arr, val) {
    Array.from(arr).some(function (item) {
        if (item.value === checkbox.value) {
            item.selected = val;
            return item.value === checkbox.value;
        }
    });
}
/**
 * Установить или снять selected для option по выбору checkbox
 * @param checkbox checkbox
 * @param selectId id Select
 */
function addRemove(checkbox, selectId) {
    var select = document.getElementById(selectId);
    var opts = select.options;
    findAndCheck(checkbox, opts, checkbox.checked);
}
/**
 * выделить или снять выделение со всех checkbox
 * @param parentCheckbox
 * @param checkClass
 */
function genSelect(parentCheckbox, checkClass) {
    var arr = document.getElementsByClassName(checkClass);
    if (parentCheckbox.checked && arr.length > 0) {
        for (var i = 0; i < arr.length; i++) {
            arr[i].checked = true;
        }
    } else {
        for (var q = 0; q < arr.length; q++) {
            arr[q].checked = false;
        }
    }
}
function checkChecked(tableId, selectId) {
    alert("SSSSSSS");
    var select = document.getElementById(selectId);
    var v = document.getElementById(tableId).rows;
    console.log('sss');
}
function sendFormAction(formId, actionStr) {
    var form = document.getElementById(formId);
    if (!actionStr.startsWith("/")) {
        actionStr = "/" + actionStr;
    }
    form.action += actionStr;
    form.submit();
}
/**
 * обработчик по кнопке редактировать на форме
 * открывает поля на редактирование для строк отмеченных галочкой + ставит значения флагов update - true upload - false
 * + делает видимой кнопку подтвердить
 * @param tbodyId id тела таблицы в которой все это происходит
 */
function edit(tbodyId) {
    var tb = document.getElementById(tbodyId);
    for (var i = 0; i < tb.rows.length; i++) {
        if (tb.rows[i].cells[1].childNodes[0].checked) {
            // console.log(tb.rows[i].cells[1].innerHTML);
            tb.rows[i].cells[1].childNodes[0].onclick = function () {
                return false
            };

            // document.getElementById('ConfirmEditingButton').hidden = false;
            // tb.rows[0].cells[0].childNodes[3].value = 'false';
            // tb.rows[0].cells[0].childNodes[5].value = 'true';
            for (var q = 2; q < tb.rows[i].cells.length; q++) {
                tb.rows[i].cells[q].firstChild.readOnly = false;
            }
        }
    }
}