// Datatables plugin
$(document).ready( function () {
    $('#table').DataTable();
} );

// Function to ask the user for delete confirmation
function confirmDelete(name) {

	// The confirmDelete() method returns true if the user clicked "OK", and false otherwise.
	if (confirm('Do you really want to delete the course ' + name + ' ?')) {

	} else {
		event.preventDefault();
	}
}