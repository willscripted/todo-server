<!-- Start body.jsp -->
<section>

    <script type="text/javascript">
        dojo.require("dojox.form.Manager");
        dojo.require("dojo/dom-form");
        dojo.require("dojox.json.schema");
        dojo.require("dojox.validate");
        dojo.require("dojox.validate.web");
        dojo.require("dojox.todo.Form");
    </script>

    <form data-dojo-type="dojox.form.Manager" id="createAccountForm"
          json-schema="/static/json/schema/todo.webapp.dto.RegistrationForm">

        <h1>Create Account</h1>

        <div class="clearfix">
            <label for="username">Username</label>
            <input id="username" name="username" type="text" maxlength="25"
                   observer="showValues" placeholder="Username" autofocus/>

            <div class="errors"></div>
        </div>


        <div class="clearfix">
            <label for="password">Password</label>
            <input id="password" name="password" type="password"
                   maxlength="40" placeholder="Password"/>

            <div class="errors"></div>
        </div>

        <div class="clearfix">
            <label for="confirm">Confirm Password</label>
            <input id="confirm" type="password" maxlength="40"
                   placeholder="Confirm Password"/>

            <div class="errors"></div>
        </div>

        <div class="clearfix">
            <label for="email">Email</label>
            <input id="email" name="email" type="text" maxlength="70"
                   placeholder="Email"/>

            <div class="errors"></div>
        </div>

        <div class="clearfix">
            <label for="accept">Accept <a href="/tos" target="_blank">Terms of
                Service</a></label>
            <input id="accept" type="checkbox"
                   value="true"/>

            <div class="errors"></div>
        </div>
        <button type="submit">Submit</button>

        <script type="text/javascript">

            var responseHandlers = {
                404:function () {
                    window.location = "/registration/";
                },
                400:function () {
                    $("form input#username").siblings("div.errors").text("username taken");
                }
            }

            var customValidators = [
                { // Email check
                    test:function () {
                        var email = $("form input#email").val();
                        return (dojox.validate.isEmailAddress(email));
                    },
                    error:{
                        property:"email",
                        message:"invalid email"
                    }
                },
                { // Password confirm
                    test:function () {
                        var password = $("form input#password").val();
                        var conf = $("form input#confirm").val();
                        return password === conf;
                    },
                    error:{
                        property:"confirm",
                        message:"password must match"
                    }
                },
                {
                    test:function () {
                        return $("form input#accept").is(":checked");
                    },
                    error:{
                        property:"accept",
                        message:"Users must accept terms of service"
                    }
                }
            ];

            var todoForm = new todo.Form({'formId':"createAccountForm"});
            todoForm.setContentType("application/todo.webapp.dto.RegistrationForm+json");
            todoForm.setCustomValidation(customValidators);
            todoForm.setResponseHandler(responseHandlers);
            todoForm.getURI = function () {
                return "/api/users/";
            }
            todoForm.setMethod("POST");

        </script>

    </form>


</section>
<!-- End body.jsp -->