/**
 * Created by IntelliJ IDEA.
 * User: will
 * Date: 10/30/11
 * Time: 9:27 AM
 * To change this template use File | Settings | File Templates.
 */


/**
 * Get and set projects and goals
 */

// Get projects
$.ajax({
           url:"/services/project/options/",
           dataType:'json',
           data:
               [
               ],
           success:function (data, textStatus, jqXHR) {
               // Set project options
               var projectSelect = $("select#projectSelector");
               for (var i in data) {
                   projectSelect
                       .append("<option value='" + data[i] + "'>" + i +
                               "</option>");
               }
           }
       });


// Get goals
$.ajax({
           url:"/services/goal/options/",
           dataType:'json',
           data:
               [
               ],
           success:function (data, textStatus, jqXHR) {
               // Set goal options
               var goalSelect = $("select#goalSelector");
               for (var i in data) {
                   goalSelect
                       .append("<option value='" + data[i] + "'>" + i +
                               "</option>");
               }
           }
       });