<!-- Start body.jsp -->
<section>

    <script type="text/javascript">
        dojo.require("dojox.form.Manager");
        dojo.require("dojo/dom-form");
        dojo.require("dojox.json.schema");
        dojo.require("dojox.validate");
        dojo.require("dojox.validate.web");
    </script>

    <form data-dojo-type="dojox.form.Manager" id="createAccountForm"
          json-schema="/static/json/schema/RegistrationForm.json">

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
            <label for="accept">Accept <a href="/tos" target="_blank">Terms of Service</a></label>
            <input id="accept" name="agreeToTOS" type="checkbox"
                   value="true" />

            <div class="errors"></div>
        </div>
        <button type="submit">Submit</button>

        <script type="text/javascript">

            // Some helper functions
            function addErrors(inputName, message) {
                var input = $("#" + formId).find('input#' + inputName);

                input
                        .siblings("div.errors")
                        .text(message);
            }


            // Set up form
            var formId = "createAccountForm";

            // Get schema
            var schema;
            var schemaLocation = $("form").attr("json-schema");
            $.ajax(schemaLocation, {
                dataType:"json",
                success:function (data) {
                    schema = data;
                    console.log(schema);
                }
            });

            // Attach event listener
            var form = dojo.byId(formId);
            dojo.connect(form, "onsubmit", function (event) {
                dojo.stopEvent(event);
                $("div.errors").empty();


                // Executed for each validation

                // Get form as json
                var instance = dojo.formToJson("createAccountForm");

                // Schema validation
                var errors =
                        dojox.json.schema.validate(JSON.parse(instance),
                                                   schema).errors;

                // Custom validation

                // Email
                var email = $("form input#email").val();
                if (!dojox.validate.isEmailAddress(email)) {

                    var err = { "property":"email",
                        "message":"invalid email"};
                    errors[errors.length] = err;
                }

                // Same passwords
                var password = $("form input#password").val();
                var conf = $("form input#confirm").val();
                if (password != conf) {
                    var err = { "property":"confirm",
                        "message":"password must match"};
                    errors[errors.length] = err;
                }

                // Accept TOS
                var accepted = $("form input#accept").is(":checked");
                if (!accepted) {
                    var err = {
                        "property":"accept",
                        "message":"Users must accept terms of service"
                    };
                    errors[errors.length] = err;
                }

                // Add errors
                for (e in errors) {
                    var prop = errors[e]['property'];
                    var val = errors[e]['message'];
                    addErrors(prop, val);
                }

                if (errors.length == 0) {

                    $.ajax("/api/users/" + $("form input#username").val() + "/"
                , {
                        data: instance,
                        contentType:"application/todo.domain.RegistrationForm+json",
                        type:"PUT",
                        success:function () {
                            window.location = "/registration/success";
                        },
                        statusCode:{
                            404:function () {
                                window.location = "/registration/";
                            },
                            400:function () {
                                $("form input#username").siblings("div.errors").text("username taken");
                            }
                        }
                    });
                }

            });
        </script>

    </form>


</section>
<!-- End body.jsp -->