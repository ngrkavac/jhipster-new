(function() {
    'use strict';

    angular
        .module('jhipsterNewApp')
        .controller('Label_translationController', Label_translationController);

    Label_translationController.$inject = ['Label_translation'];

    function Label_translationController(Label_translation) {

        var vm = this;

        vm.label_translations = [];

        loadAll();

        function loadAll() {
            Label_translation.query(function(result) {
                vm.label_translations = result;
                vm.searchQuery = null;
            });
        }
    }
})();
