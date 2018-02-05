(function() {
    'use strict';

    angular
        .module('jhipsterNewApp')
        .controller('Label_translationDetailController', Label_translationDetailController);

    Label_translationDetailController.$inject = ['$scope', '$rootScope', '$stateParams', 'previousState', 'entity', 'Label_translation', 'Label'];

    function Label_translationDetailController($scope, $rootScope, $stateParams, previousState, entity, Label_translation, Label) {
        var vm = this;

        vm.label_translation = entity;
        vm.previousState = previousState.name;

        var unsubscribe = $rootScope.$on('jhipsterNewApp:label_translationUpdate', function(event, result) {
            vm.label_translation = result;
        });
        $scope.$on('$destroy', unsubscribe);
    }
})();
