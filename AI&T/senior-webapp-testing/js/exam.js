var obj01 = new autoComplete({
    selector: '#autocomplete01',
    minChars: 0,
    source: function (term, suggest) {
        term = term.toLowerCase();
        // var choices = [['Australia', 'au'], ['Austria', 'at'], ['Brasil', 'br'], ['Bulgaria', 'bg'], ['Canada', 'ca'], ['China', 'cn'], ['Czech Republic', 'cz'], ['Denmark', 'dk'], ['Finland', 'fi'], ['France', 'fr'], ['Germany', 'de'], ['Hungary', 'hu'], ['India', 'in'], ['Italy', 'it'], ['Japan', 'ja'], ['Netherlands', 'nl'], ['Norway', 'no'], ['Portugal', 'pt'], ['Romania', 'ro'], ['Russia', 'ru'], ['Spain', 'es'], ['Swiss', 'ch'], ['Turkey', 'tr'], ['USA', 'us']];
        var choices = TABLE_DATA;
        var suggestions = [];
        for (i = 0; i < choices.length; i++) {
            if (~(choices[i].name + ' ' + choices[i].id).toLowerCase().indexOf(term)) {
                choices[i].name = choices[i].name.replace(/[^\w\s]/gi, '');
                suggestions.push(choices[i]);
            }
        }
        suggest(suggestions);
    },
    renderItem: function (item, search) {
        search = search.replace(/[-\/\\^$*+?.()|[\]{}]/g, '\\$&amp;');
        var re = new RegExp("(" + search.split(' ').join('|') + ")", "gi");
        return '<div class="autocomplete-suggestion" data-langname="' + item.name + '" data-lang="' + item.id + '" data-val="' + search + '"><img class="item_image" src="' + item.thumbnailUrl + '"> ' + item.name.replace(re, "<b>$1</b>") + '</div>';
    },
    onSelect: function (e, term, item) {
        console.log('Item "' + item.getAttribute('data-langname') + ' (' + item.getAttribute('data-lang') + ')" selected by ' + (e.type == 'keydown' ? 'pressing enter' : 'mouse click') + '.');
        document.getElementById('autocomplete01').value = item.getAttribute('data-langname') + ' (' + item.getAttribute('data-lang') + ')';
    }
});