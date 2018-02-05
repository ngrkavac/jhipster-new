(function() {
    'use strict';

    angular
        .module('jhipsterNewApp')
        .controller('AttachmentController', AttachmentController);

    AttachmentController.$inject = ['DataUtils', 'Attachment'];

    function AttachmentController(DataUtils, Attachment) {

        var vm = this;

        vm.attachments = [];
        vm.openFile = DataUtils.openFile;
        vm.byteSize = DataUtils.byteSize;

        loadAll();

        function loadAll() {
            Attachment.query(function(result) {
                vm.attachments = result;
                vm.searchQuery = null;
            });
        }
    }
})();
