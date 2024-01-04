<template>
  <ck-editor v-model="editorContent" :editor="editor" :config="editorConfig" />
</template>

<script>
import { defineComponent } from "vue";
import CKEditor from "@ckeditor/ckeditor5-vue";
import ClassicEditor from "@ckeditor/ckeditor5-build-classic";
import UploadAdapter from "@/cKEditor/UploadAdapter";

export default defineComponent({
  components: {
    "ck-editor": CKEditor.component,
  },
  data() {
    return {
      // ✅ v-model
      editorContent: "",
      // ✅ :editor
      editor: ClassicEditor,
      // ✅ :config
      editorConfig: {
        toolbar: [
          "heading",
          "|",
          "fontBackgroundColor",
          "fontColor",
          "fontSize",
          "bold",
          "italic",
          "|",
          "alignment",
          "bulletedList",
          "numberedList",
          "indent",
          "outdent",
          "|",
          "imageUpload",
          "insertTable",
          "link",
          "|",
          "undo",
          "redo",
        ],
        table: {
          contentToolbar: [
            "tableColumn",
            "tableRow",
            "mergeTableCells",
            "tableProperties",
            "tableCellProperties",
          ],
        },
        image: {
          resize: true,
          toolbar: [
            "imageStyle:alignLeft",
            "imageStyle:alignRight",
            "imageStyle:inline",
            "imageStyle:side",
          ],
        },
        extraPlugins: [ function(editor) {editor.plugins.get( 'FileRepository' ).createUploadAdapter = ( loader ) => {
          return new UploadAdapter( loader );
        }}]
      },
    };
  },
});

</script>
