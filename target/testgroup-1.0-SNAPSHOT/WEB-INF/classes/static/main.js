$('document').ready(function(){
    $('.table .btne').on('click',function(event){

        event.preventDefault();

        $('#editModal').modal();
    });
});