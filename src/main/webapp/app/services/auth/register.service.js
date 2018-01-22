(function () {
    'use strict';

    angular
        .module('jhipsterNewApp')
        .factory('Register', Register);

    Register.$inject = ['$resource'];

    function Register ($resource) {
        return $resource('api/register', {}, {});
    }
})();
