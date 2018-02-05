(function() {
    'use strict';

    angular
        .module('jhipsterNewApp')
        .controller('Label_translationDialogController', Label_translationDialogController);

    Label_translationDialogController.$inject = ['$timeout', '$scope', '$stateParams', '$uibModalInstance', 'entity', 'Label_translation', 'Label'];

    function Label_translationDialogController ($timeout, $scope, $stateParams, $uibModalInstance, entity, Label_translation, Label) {
        var vm = this;

        vm.label_translation = entity;
        vm.clear = clear;
        vm.save = save;
        vm.labels = Label.query();

        $timeout(function (){
            angular.element('.form-group:eq(1)>input').focus();
        });

        function clear () {
            $uibModalInstance.dismiss('cancel');
        }

        function save () {
            vm.isSaving = true;
            if (vm.label_translation.id !== null) {
                Label_translation.update(vm.label_translation, onSaveSuccess, onSaveError);
            } else {
                Label_translation.save(vm.label_translation, onSaveSuccess, onSaveError);
            }
        }

        function onSaveSuccess (result) {
            $scope.$emit('jhipsterNewApp:label_translationUpdate', result);
            $uibModalInstance.close(result);
            vm.isSaving = false;
        }

        function onSaveError () {
            vm.isSaving = false;
        }


    }
})();
