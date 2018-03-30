(function() {
    'use strict';

    angular
        .module('jhipsterNewApp')
        .config(stateConfig);

    stateConfig.$inject = ['$stateProvider'];

    function stateConfig($stateProvider) {
        $stateProvider
        .state('label-translation', {
            parent: 'entity',
            url: '/label-translation',
            data: {
                authorities: ['ROLE_USER'],
                pageTitle: 'Label_translations'
            },
            views: {
                'content@': {
                    templateUrl: 'app/entities/label-translation/label-translations.html',
                    controller: 'Label_translationController',
                    controllerAs: 'vm'
                }
            },
            resolve: {
            }
        })
        .state('label-translation-detail', {
            parent: 'label-translation',
            url: '/label-translation/{id}',
            data: {
                authorities: ['ROLE_USER'],
                pageTitle: 'Label_translation'
            },
            views: {
                'content@': {
                    templateUrl: 'app/entities/label-translation/label-translation-detail.html',
                    controller: 'Label_translationDetailController',
                    controllerAs: 'vm'
                }
            },
            resolve: {
                entity: ['$stateParams', 'Label_translation', function($stateParams, Label_translation) {
                    return Label_translation.get({id : $stateParams.id}).$promise;
                }],
                previousState: ["$state", function ($state) {
                    var currentStateData = {
                        name: $state.current.name || 'label-translation',
                        params: $state.params,
                        url: $state.href($state.current.name, $state.params)
                    };
                    return currentStateData;
                }]
            }
        })
        .state('label-translation-detail.edit', {
            parent: 'label-translation-detail',
            url: '/detail/edit',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/label-translation/label-translation-dialog.html',
                    controller: 'Label_translationDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: ['Label_translation', function(Label_translation) {
                            return Label_translation.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('^', {}, { reload: false });
                }, function() {
                    $state.go('^');
                });
            }]
        })
        .state('label-translation.new', {
            parent: 'label-translation',
            url: '/new',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/label-translation/label-translation-dialog.html',
                    controller: 'Label_translationDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: function () {
                            return {
                                translation_language: null,
                                version: null,
                                owner: null,
                                id: null
                            };
                        }
                    }
                }).result.then(function() {
                    $state.go('label-translation', null, { reload: 'label-translation' });
                }, function() {
                    $state.go('label-translation');
                });
            }]
        })
        .state('label-translation.edit', {
            parent: 'label-translation',
            url: '/{id}/edit',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/label-translation/label-translation-dialog.html',
                    controller: 'Label_translationDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: ['Label_translation', function(Label_translation) {
                            return Label_translation.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('label-translation', null, { reload: 'label-translation' });
                }, function() {
                    $state.go('^');
                });
            }]
        })
        .state('label-translation.delete', {
            parent: 'label-translation',
            url: '/{id}/delete',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/label-translation/label-translation-delete-dialog.html',
                    controller: 'Label_translationDeleteController',
                    controllerAs: 'vm',
                    size: 'md',
                    resolve: {
                        entity: ['Label_translation', function(Label_translation) {
                            return Label_translation.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('label-translation', null, { reload: 'label-translation' });
                }, function() {
                    $state.go('^');
                });
            }]
        });
    }

})();
