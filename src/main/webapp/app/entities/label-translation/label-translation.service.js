(function() {
    'use strict';
    angular
        .module('jhipsterNewApp')
        .factory('Label_translation', Label_translation);

    Label_translation.$inject = ['$resource'];

    function Label_translation ($resource) {
        var resourceUrl =  'api/label-translations/:id';

        return $resource(resourceUrl, {}, {
            'query': { method: 'GET', isArray: true},
            'get': {
                method: 'GET',
                transformResponse: function (data) {
                    if (data) {
                        data = angular.fromJson(data);
                    }
                    return data;
                }
            },
            'update': { method:'PUT' }
        });
    }
})();
