package platform.utils;

import platform.model.ProgramDto;

public class HtmlCreator {

    public static final String SEND_FUNCTION = "function send() { " +
                                               "let object = { \"code\": document.getElementById(\"code_snippet\").value }; let json = JSON.stringify(object); let xhr = new XMLHttpRequest(); " +
                                               "xhr.open(\"POST\", '/api/code/new', false); xhr.setRequestHeader('Content-type', 'application/json; charset=utf-8');" +
                                               "xhr.send(json); if (xhr.status == 200) { alert(\"Success!\"); } }";

    public String programPage(ProgramDto programDto) {
        return "<html><head><title>Code</title></head><body><span id=\"load_date\">" +
               programDto.getDate() + "</span><pre id=\"code_snippet\">" +
               programDto.getCode() + "</pre></body></html> " + SEND_FUNCTION;
    }
}
