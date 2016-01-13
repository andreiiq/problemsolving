$(document).ready(function() {
$("#profile-picture-input").change(readURL);
	function readURL() {
		if (this.files && this.files[0]) {
			var reader = new FileReader();
			$(reader).load(function(e) {
				$('#profile-picture-selection').attr('src', e.target.result)
			});
			reader.readAsDataURL(this.files[0]);
		}
	}
});