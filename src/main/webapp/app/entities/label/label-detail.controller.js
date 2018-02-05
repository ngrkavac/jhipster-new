(function() {
    'use strict';

    angular
        .module('jhipsterNewApp')
        .controller('LabelDetailController', LabelDetailController);

    LabelDetailController.$inject = ['$scope', '$rootScope', '$stateParams', 'previousState', 'entity', 'Label'];

    function LabelDetailController($scope, $rootScope, $stateParams, previousState, entity, Label) {
        var vm = this;

        vm.label = entity;
        vm.previousState = previousState.name;

        var unsubscribe = $rootScope.$on('jhipsterNewApp:labelUpdate', function(event, result) {
            vm.label = result;
        });
        $scope.$on('$destroy', unsubscribe);
    }
})();
