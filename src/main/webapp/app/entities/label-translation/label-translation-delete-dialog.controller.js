(function() {
    'use strict';

    angular
        .module('jhipsterNewApp')
        .controller('Label_translationDeleteController',Label_translationDeleteController);

    Label_translationDeleteController.$inject = ['$uibModalInstance', 'entity', 'Label_translation'];

    function Label_translationDeleteController($uibModalInstance, entity, Label_translation) {
        var vm = this;

        vm.label_translation = entity;
        vm.clear = clear;
        vm.confirmDelete = confirmDelete;

        function clear () {
            $uibModalInstance.dismiss('cancel');
        }

        function confirmDelete (id) {
            Label_translation.delete({id: id},
                function () {
                    $uibModalInstance.close(true);
                });
        }
    }
})();
