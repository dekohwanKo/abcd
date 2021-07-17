/**
 * 
 */
function readURL(input){
		var file = input.files[0] //파일에 대한 정보
		if(file != ''){
			var reader = new FileReader();
			reader.readAsDataURL(file);// 파일 읽어오기
			reader.onload = function(e){ // 읽어온 파일 표현
				$('#preview').attr('src', e.target.result)
			}
		}
	}