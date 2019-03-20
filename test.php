<!DOCTYPE html>

<?php

    #Variables

    #Feel free to change these.

        static $firstname = "Joe";

        static $lastname = "Hardy";

        static $bgimglocation = "";

        static $profileimglocation = "";

        static $profileimgsize = 100;

        static $biography = "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. At volutpat diam ut venenatis tellus in. Pretium vulputate sapien nec sagittis aliquam malesuada. A iaculis at erat pellentesque adipiscing commodo elit. At risus viverra adipiscing at in tellus integer. Augue interdum velit euismod in pellentesque massa placerat. Nisl condimentum id venenatis a condimentum vitae. Egestas maecenas pharetra convallis posuere morbi leo. Dapibus ultrices in iaculis nunc. Egestas integer eget aliquet nibh.<br><br>

 

Malesuada fames ac turpis egestas integer eget aliquet nibh. Mauris in aliquam sem fringilla ut morbi tincidunt augue interdum. Sed blandit libero volutpat sed. Sem viverra aliquet eget sit amet tellus cras adipiscing enim. Urna nunc id cursus metus aliquam eleifend. Porttitor rhoncus dolor purus non enim praesent elementum. Mauris a diam maecenas sed enim ut sem viverra aliquet. Sed felis eget velit aliquet sagittis id. In fermentum posuere urna nec. Etiam non quam lacus suspendisse faucibus interdum posuere lorem ipsum. Quis ipsum suspendisse ultrices gravida dictum. Massa placerat duis ultricies lacus sed turpis. Ac feugiat sed lectus vestibulum mattis ullamcorper. Id porta nibh venenatis cras sed felis. Sodales neque sodales ut etiam sit amet. Purus semper eget duis at tellus at urna. Curabitur vitae nunc sed velit.<br><br>

 

Vitae nunc sed velit dignissim sodales. Tincidunt id aliquet risus feugiat. Velit euismod in pellentesque massa placerat duis ultricies lacus sed. Viverra adipiscing at in tellus. Donec adipiscing tristique risus nec feugiat. Tempor nec feugiat nisl pretium fusce id velit. Bibendum ut tristique et egestas. Leo integer malesuada nunc vel risus. Mattis vulputate enim nulla aliquet. Mauris pharetra et ultrices neque ornare aenean euismod elementum. Rhoncus dolor purus non enim praesent elementum facilisis leo vel. Magna ac placerat vestibulum lectus mauris ultrices eros in. Convallis convallis tellus id interdum velit laoreet id donec.<br><br>

 

Molestie ac feugiat sed lectus vestibulum mattis. Enim nec dui nunc mattis enim. Lectus vestibulum mattis ullamcorper velit sed ullamcorper morbi tincidunt ornare. Nibh sit amet commodo nulla facilisi nullam vehicula. Eu mi bibendum neque egestas congue quisque. Massa tincidunt nunc pulvinar sapien. Ultrices tincidunt arcu non sodales neque sodales. Faucibus scelerisque eleifend donec pretium vulputate sapien. Pellentesque eu tincidunt tortor aliquam. A diam sollicitudin tempor id eu nisl nunc mi ipsum. Tincidunt praesent semper feugiat nibh sed pulvinar proin. Ultrices vitae auctor eu augue ut lectus arcu. Quis eleifend quam adipiscing vitae proin sagittis nisl rhoncus mattis. Cursus turpis massa tincidunt dui ut ornare lectus sit amet.<br><br>

 

Tempus egestas sed sed risus pretium quam. In eu mi bibendum neque egestas congue. Sit amet cursus sit amet dictum sit amet justo donec. Pulvinar elementum integer enim neque volutpat ac tincidunt vitae semper. Enim ut sem viverra aliquet eget. Dictum sit amet justo donec. Sed nisi lacus sed viverra tellus in. Eget magna fermentum iaculis eu non. Sollicitudin aliquam ultrices sagittis orci a scelerisque. Sem viverra aliquet eget sit amet tellus cras adipiscing. Ullamcorper malesuada proin libero nunc consequat interdum varius sit. Quam adipiscing vitae proin sagittis nisl rhoncus mattis. Praesent elementum facilisis leo vel fringilla est ullamcorper eget nulla. Platea dictumst quisque sagittis purus sit amet volutpat consequat mauris. Nunc congue nisi vitae suscipit. Et tortor consequat id porta.";

 

 

    #Variable Validation

        #Background image settings

            if ($bgimglocation == "") {

                $bgimglocation = "https://cdn.athemes.com/wp-content/uploads/Original-JPG-Image.jpg";

            }

 

        #Profile image settings

            static $profileimg = "style=\"float:left; margin-bottom:1%; margin-right:0.8%;";

            if ($profileimgsize >= 0) {

                if ($profileimgsize >= 0) {

                    $profileimg = $profileimg . "height:" . $profileimgsize . "px;";

                }

                if ($profileimglocation == "") {

                    $profileimglocation = "https://eform.etixdubai.com/App_Themes/DefaultNew/images/profile.png";

                }

            }

            $profileimg = $profileimg . "\" src=\"" . $profileimglocation . "\" alt=\"Profile Picture\"";

?>

 

 

<html lang="en-US">

    <head>

        <title>

            <?php

                print $firstname . " " . $lastname . " - Spiphi";

            ?>

        </title>

        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-beta.2/css/bootstrap.min.css" integrity="sha384-PsH8R72JQ3SOdhVi3uxftmaW6Vc51MKb0q5P2rRUpPvrszuE4W1povHYgTpBfshb" crossorigin="anonymous">

        <style>

            body {

                background-image: <?php echo 'url("' . $bgimglocation . '")' ?>;

                background-repeat: no-repeat;

                background-attachment: fixed;

                background-position: center center;

                background-size: cover;

                -webkit-background-size: cover;

                -moz-background-size: cover;

                -o-background-size: cover;

                margin-top: 0.8%;

                margin-left: 0.8%;

                margin-right: 0.8%;

                margin-bottom: 0.8%;

                padding-bottom: 1%;

            }

            h1 {

                background-color: #fff;

                text-align: center;

                margin-left: 10%;

                margin-right: 10%;

                margin-top: 0%;

                font-family: "Times New Roman", Times, Georgia, serif;

            }

            p {

                text-align: center;

                font-family: courier;

                -ms-user-select: none;

                -webkit-user-select: none;

                -moz-user-select: -moz-none;

            }

            div {

                padding-left: 0.5%;

                padding-right: 0.5%;

            }

            .menuItem {

               

            }

            #menu {

                border-right: 6px solid #FCF6C4;

                border-left: 6px solid #FCF6C4;

                border-radius: 12px;

                background-color: rgba(255, 255, 200, 0.2);

                text-align: right;

                margin-left: 40%;

                margin-right: 10%;

                padding-top: 0.005%;

                padding-right: 0.5%;

                font-family: "Times New Roman", Times, Georgia, serif;

            }

            #outsideDiv {


                background-color: rgba(255, 255, 255, 0.5);

                margin-left: 10%;

                margin-right: 10%;

            }

            #homeLink {

                color: blue;

                font-variant: petite-caps;

                font-size: 65%;

                text-decoration: underline overline blue;

            }

            #top {

                font-size: 300%;

                font-variant-caps: small-caps;

                font-weight: 600;

            }

            #biography {

 

            }

        </style>

    </head>

    <body>

        <h3 id="menu">

            <a href="spiphin://home" id="homeLink" class="menuItem">

                Home

            </a>

        </h3>

        <h1 id="top">

            <?php

                print $firstname . " " . $lastname;

            ?>

        </h1>

        <div id="outsideDiv">

            <div id="infoDiv">

                <img

                    <?php

                        print $profileimg;

                    ?>

                >

                <div id="biography">

                    <?php

                        print $biography

                    ?>

                </div>

            </div>

            <div>

                <p>

                    Hi

                </p>

            </div>

            <br>

            <br>

            <br>

        </div>

        <h1>

            <a href="spiphin://home" id="homeLink">

                Home

            </a>

        </h1>

        <script src="https://code.jquery.com/jquery-3.2.1.slim.min.js" integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN" crossorigin="anonymous"></script>

        <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.3/umd/popper.min.js" integrity="sha384-vFJXuSJphROIrBnz7yo7oB41mKfc8JzQZiCq4NCceLEaO4IHwicKwpJf9c9IpFgh" crossorigin="anonymous"></script>

        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-beta.2/js/bootstrap.min.js" integrity="sha384-alpBpkh1PFOepccYVYDB4do5UnbKysX5WZXm3XxPqe5iKTfUKjNkCk9SaVuEZflJ" crossorigin="anonymous"></script>

   </body>

</html>