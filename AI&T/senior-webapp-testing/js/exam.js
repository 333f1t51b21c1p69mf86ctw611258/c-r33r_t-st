(function () {
    var names = null;

    var obj01 = new autoComplete({
        selector: '#autocomplete01',
        minChars: 0,
        source: function (term, suggest) {
            term = term.toLowerCase();
            var choices = TABLE_DATA;
            var suggestions = [];
            for (i = 0; i < choices.length; i++) {
                if (~(choices[i].name + ' ' + choices[i].id).toLowerCase().indexOf(term)) {
                    choices[i].name = choices[i].name.replace(/[^\w\s]/gi, '');
                    suggestions.push(choices[i]);
                }
            }
            suggest(suggestions);

            loadInputHistory();
        },
        renderItem: function (item, search) {
            search = search.replace(/[-\/\\^$*+?.()|[\]{}]/g, '\\$&amp;');
            var re = new RegExp("(" + search.split(' ').join('|') + ")", "gi");
            return '<div class="autocomplete-suggestion" data-langname="' + item.name + '" data-lang="' + item.id + '" data-val="' + search + '"><img class="item-image" src="' + item.thumbnailUrl + '"> ' + item.name.replace(re, "<b>$1</b>") + '</div>';
        },
        onSelect: function (e, term, item) {
            console.log('Item "' + item.getAttribute('data-langname') + ' (' + item.getAttribute('data-lang') + ')" selected by ' + (e.type == 'keydown' ? 'pressing enter' : 'mouse click') + '.');
            document.getElementById('autocomplete01').value = item.getAttribute('data-langname'); // + ' (' + item.getAttribute('data-lang') + ')';

            saveInputHistory(document.getElementById('autocomplete01').value);
        }
    });

    function loadInputHistory() {
        if (names == null) {
            if (typeof (Storage) !== "undefined") {
                if (localStorage.getItem("names")) {
                    names = JSON.parse(localStorage.getItem("names"));
                } else {
                    names = [];
                }

                if (names.length > 0) {
                    names.forEach(element => {
                        addInputHistoryUL(element);
                    });
                }

                console.log("load: ");
                console.info(names);
            } else {
                names = [];
            }
        }
    }

    function saveInputHistory(newName) {
        names.push(newName);

        if (typeof (Storage) !== "undefined") {
            localStorage.setItem("names", JSON.stringify(names));

            addInputHistoryUL(newName);

            console.log("save: ");
            console.info(names);
        }
    }

    function addInputHistoryUL(liValue) {
        var ul = document.getElementById("inputhistory");
        var li = document.createElement("li");
        li.appendChild(document.createTextNode(liValue));
        ul.appendChild(li);
    }

    document.getElementById("cleanInputHistory").onclick = function () {
        names = [];
        localStorage.setItem("names", JSON.stringify(names));

        var ul = document.getElementById("inputhistory");
        if (ul) {
            while (ul.firstChild) {
                ul.removeChild(ul.firstChild);
            }
        }
    }
}());