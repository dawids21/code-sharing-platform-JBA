package platform.utils;

import platform.model.ProgramDto;

public class HtmlCreator {

    public static final String SEND_FUNCTION = "<script>function send() { " +
                                               "let object = { \"code\": document.getElementById(\"code_snippet\").value }; let json = JSON.stringify(object); let xhr = new XMLHttpRequest(); " +
                                               "xhr.open(\"POST\", '/api/code/new', false); xhr.setRequestHeader('Content-type', 'application/json; charset=utf-8');" +
                                               "xhr.send(json); if (xhr.status == 200) { alert(\"Success!\"); } }</script>";

    public static final String STYLE =
             "<style>#load_date { color: green; } #code_snippet { background-color: lightgrey; border: 2px solid black; }</style>";

    public String programPage(ProgramDto programDto) {
        return "<html><head>" + STYLE +
               "<title>Code</title></head><body><span id=\"load_date\">" +
               programDto.getDate() + "</span><pre id=\"code_snippet\">" +
               programDto.getCode() + "</pre></body></html>";
    }

    public String sendProgramPage() {
        return "<html><head>" + STYLE +
               "<title>Create</title></head><body><div><textarea id=\"code_snippet\">" +
               "</textarea></div><div><button id=\"send_snippet\" type=\"submit\" onclick=\"send()\">Submit</button></div></body>" +
               SEND_FUNCTION + "</html>";
    }
}
