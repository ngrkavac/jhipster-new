(function() {
    'use strict';
    angular
        .module('jhipsterNewApp')
        .factory('Attachment', Attachment);

    Attachment.$inject = ['$resource', 'DateUtils'];

    function Attachment ($resource, DateUtils) {
        var resourceUrl =  'api/attachments/:id';

        return $resource(resourceUrl, {}, {
            'query': { method: 'GET', isArray: true},
            'get': {
                method: 'GET',
                transformResponse: function (data) {
                    if (data) {
                        data = angular.fromJson(data);
                        data.created_on = DateUtils.convertLocalDateFromServer(data.created_on);
                    }
                    return data;
                }
            },
            'update': {
                method: 'PUT',
                transformRequest: function (data) {
                    var copy = angular.copy(data);
                    copy.created_on = DateUtils.convertLocalDateToServer(copy.created_on);
                    return angular.toJson(copy);
                }
            },
            'save': {
                method: 'POST',
                transformRequest: function (data) {
                    var copy = angular.copy(data);
                    copy.created_on = DateUtils.convertLocalDateToServer(copy.created_on);
                    return angular.toJson(copy);
                }
            }
        });
    }
})();
