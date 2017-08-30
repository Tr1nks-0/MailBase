;

/**
 * найти в массиве сущность по значению совпадающему со значением checkbox и установить ему значение
 * @param checkbox checkbox
 * @param arr массив
 * @param val значение
 */
function findAndCheck(checkbox, arr, val) {
    Array.from(arr).some(function (item) {
        if (item.value === checkbox.value) {
            item.selected = val;
            return item.value === checkbox.value;
        }
    });
}

/**
 * Установить или снять selected для option по выбору checkbox без создания span
 * @param checkbox checkbox
 * @param selectId id Select
 */
function addRemoveNoAdd(checkbox, selectId) {
    var select = document.getElementById(selectId);
    var opts = select.options;
    findAndCheck(checkbox, opts, checkbox.checked);
}
    /**
     }
 * Установить или снять selected для option по выбору checkbox с созданием span
 * @param checkbox checkbox
 * @param selectId id Select
 * @param filterDivId id div with view of div with selected vars
 */
function addRemove(checkbox, selectId, filterDivId) {
    var select = document.getElementById(selectId);
    var opts = select.options;
    findAndCheck(checkbox, opts, checkbox.checked);
    var filterDiv = document.getElementById(filterDivId);
    var id = checkbox.id + "_filteredDiv_" + checkbox.value;
    if (checkbox.checked && document.getElementById(id) === null) {
        var s = document.createElement('span');
        s.textContent = checkbox.labels[0].innerText;
        s.id = id;
        filterDiv.appendChild(s);
    } else if (!checkbox.checked && document.getElementById(id) !== null) {
        document.getElementById(id).remove();
    }
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
            arr[i].onclick();
        }
    } else {
        for (var q = 0; q < arr.length; q++) {
            arr[q].checked = false;
            arr[q].onclick();
        }
    }
}

/**
 *Установить действие для формы и отправить
 * дописывает действие к уже заданному действию формы
 * @param formId id формы для отправки
 * @param actionStr имя действия ( может как содержать так и не содержать "/")
 */
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
 * @param checkboxClass имя класса checkbox отвечающего за отметку строки
 * @param inputClass первая часть имени класса отвечающего за имя input, вторая часть имени - нижнее подчеркивание и значение checkbox строки
 * @param normalButtonDivId id
 * @param commitButtonId id
 */
function edit(checkboxClass, inputClass, normalButtonDivId, commitButtonId) {
    var ids = document.getElementsByClassName(checkboxClass);
    Array.from(ids).some(function (item) {
        if (item.checked && ids.length > 0) {
            var inps = document.getElementsByClassName(inputClass + "_" + item.value);
            Array.from(inps).some(function (inp) {
                inp.readOnly = false;
            });
        }
    });
    document.getElementById(normalButtonDivId).hidden = true;
    document.getElementById(commitButtonId).hidden = false;
}