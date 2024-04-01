import ClassicEditor from "@ckeditor/ckeditor5-build-classic";

const makeBoundary = () => {
  let text = "";
  const possible = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789_";
  for (let i = 0; i < 20; i++) {
    text += possible.charAt(Math.floor(Math.random() * possible.length));
  }
  return text;
};
class UploadAdapter {
  constructor(loader) {
    // The file loader instance to use during the upload.
    this.loader = loader;
  }

  // Starts the upload process.
  upload() {
    var reader  = new FileReader();

    return new Promise( ( resolve, reject ) => {
      reader.addEventListener( 'load', () => {
        resolve( { default: reader.result } );
      });

      reader.addEventListener( 'error', err => {
        reject( err );
      });

      reader.addEventListener( 'abort', () => {
        reject();
      });

      this.loader.file.then( file => {
        reader.readAsDataURL( file );
      });
    })
  }

  // Aborts the upload process.
  abort() {
    if (this.xhr) {
      this.xhr.abort();
    }
  }

  // Initializes the XMLHttpRequest object using the URL passed to the constructor.
  _initRequest() {
    const xhr = (this.xhr = new XMLHttpRequest());
    console.log('Initializing request...');
    // Note that your request may look different. It is up to you and your editor
    // integration to choose the right communication channel. This example uses
    // a POST request with JSON as a data structure but your configuration
    // could be different.
    xhr.open('POST', 'http://localhost:8080/api/study/uploadFile', true);
    xhr.setRequestHeader("enctype", `multipart/form-data; charset=utf-8; boundary=${makeBoundary()}`);
    xhr.responseType = 'json';
  }


  // Initializes XMLHttpRequest listeners.
  _initListeners(resolve, reject, file) {
    console.log('Sending request...', file);
    const xhr = this.xhr;
    const loader = this.loader;
    const genericErrorText = `Couldn't upload file: ${file.name}.`;

    xhr.addEventListener('error', () => reject(genericErrorText));
    xhr.addEventListener('abort', () => reject());
    xhr.addEventListener('load', () => {
      const response = xhr.response;

      // This example assumes the XHR server's "response" object will come with
      // an "error" which has its own "message" that can be passed to reject()
      // in the upload promise.
      //
      // Your integration may handle upload errors in a different way so make sure
      // it is done properly. The reject() function must be called when the upload fails.
      if (!response || response.error) {
        return reject(response && response.error ? response.error.message : genericErrorText);
      }

      // If the upload is successful, resolve the upload promise with an object containing
      // at least the "default" URL, pointing to the image on the server.
      // This URL will be used to display the image in the content. Learn more in the
      // UploadAdapter#upload documentation.
      resolve({
        default: response.data.url
      });
    });

    // Upload progress when it is supported. The file loader has the #uploadTotal and #uploaded
    // properties which are used e.g. to display the upload progress bar in the editor
    // user interface.
    if (xhr.upload) {
      xhr.upload.addEventListener('progress', evt => {
        if (evt.lengthComputable) {
          loader.uploadTotal = evt.total;
          loader.uploaded = evt.loaded;
        }
      });
    }
  }

  // Prepares the data and sends the request.
  _sendRequest(upload) {
    // Prepare the form data.
    const data = new FormData();

    // 이미지 파일
    data.append("upload", upload);
    // 파라미터

    // Send the request.
    this.xhr.send(data);
  }
}

export default UploadAdapter;