(function() {
    'use strict';

    angular
        .module('jhipsterNewApp')
        .controller('AttachmentDialogController', AttachmentDialogController);

    AttachmentDialogController.$inject = ['$timeout', '$scope', '$stateParams', '$uibModalInstance', 'DataUtils', 'entity', 'Attachment'];

    function AttachmentDialogController ($timeout, $scope, $stateParams, $uibModalInstance, DataUtils, entity, Attachment) {
        var vm = this;

        vm.attachment = entity;
        vm.clear = clear;
        vm.datePickerOpenStatus = {};
        vm.openCalendar = openCalendar;
        vm.byteSize = DataUtils.byteSize;
        vm.openFile = DataUtils.openFile;
        vm.save = save;

        $timeout(function (){
            angular.element('.form-group:eq(1)>input').focus();
        });

        function clear () {
            $uibModalInstance.dismiss('cancel');
        }

        function save () {
            vm.isSaving = true;
            if (vm.attachment.id !== null) {
                Attachment.update(vm.attachment, onSaveSuccess, onSaveError);
            } else {
                Attachment.save(vm.attachment, onSaveSuccess, onSaveError);
            }
        }

        function onSaveSuccess (result) {
            $scope.$emit('jhipsterNewApp:attachmentUpdate', result);
            $uibModalInstance.close(result);
            vm.isSaving = false;
        }

        function onSaveError () {
            vm.isSaving = false;
        }


        vm.setFile = function ($file, attachment) {
            if ($file) {
                DataUtils.toBase64($file, function(base64Data) {
                    $scope.$apply(function() {
                        attachment.file = base64Data;
                        attachment.fileContentType = $file.type;
                    });
                });
            }
        };
        vm.datePickerOpenStatus.created_on = false;

        function openCalendar (date) {
            vm.datePickerOpenStatus[date] = true;
        }
    }
})();
