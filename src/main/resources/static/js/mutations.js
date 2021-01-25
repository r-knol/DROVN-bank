$("[class='card-collapse']").on('shown.bs.collapse', function (event) {
    let targetSelector = $(event.target).data('bs.collapse')._triggerArray[0];
    let element = $(targetSelector);
    console.log(element);

})