System.register(['@angular/platform-browser-dynamic', "./sign-in/sign-in.component", "@angular/http"], function(exports_1, context_1) {
    "use strict";
    var __moduleName = context_1 && context_1.id;
    var platform_browser_dynamic_1, sign_in_component_1, http_1;
    return {
        setters:[
            function (platform_browser_dynamic_1_1) {
                platform_browser_dynamic_1 = platform_browser_dynamic_1_1;
            },
            function (sign_in_component_1_1) {
                sign_in_component_1 = sign_in_component_1_1;
            },
            function (http_1_1) {
                http_1 = http_1_1;
            }],
        execute: function() {
            platform_browser_dynamic_1.bootstrap(sign_in_component_1.SignInComponent, http_1.HTTP_PROVIDERS);
        }
    }
});

//# sourceMappingURL=data:application/json;base64,eyJ2ZXJzaW9uIjozLCJzb3VyY2VzIjpbImJvb3QudHMiXSwibmFtZXMiOltdLCJtYXBwaW5ncyI6Ijs7Ozs7Ozs7Ozs7Ozs7OztZQUtBLG9DQUFTLENBQUMsbUNBQWUsRUFBRSxxQkFBYyxDQUFDLENBQUMiLCJmaWxlIjoiYm9vdC5qcyIsInNvdXJjZXNDb250ZW50IjpbImltcG9ydCB7IGJvb3RzdHJhcCB9IGZyb20gJ0Bhbmd1bGFyL3BsYXRmb3JtLWJyb3dzZXItZHluYW1pYyc7XG5pbXBvcnQgeyBTaWduSW5Db21wb25lbnQgfSBmcm9tIFwiLi9zaWduLWluL3NpZ24taW4uY29tcG9uZW50XCI7XG5pbXBvcnQgeyBIVFRQX1BST1ZJREVSUyB9IGZyb20gXCJAYW5ndWxhci9odHRwXCI7XG5cblxuYm9vdHN0cmFwKFNpZ25JbkNvbXBvbmVudCwgSFRUUF9QUk9WSURFUlMpO1xuIl0sInNvdXJjZVJvb3QiOiIvc291cmNlLyJ9
