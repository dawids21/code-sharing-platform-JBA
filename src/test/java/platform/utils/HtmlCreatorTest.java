package platform.utils;

import org.junit.jupiter.api.Test;
import platform.model.ProgramDto;

import static org.assertj.core.api.Assertions.assertThat;

class HtmlCreatorTest {

    @Test
    void return_html_page_with_added_program() {
        HtmlCreator htmlCreator = new HtmlCreator();
        ProgramDto programDto = new ProgramDto("main()", "2020-12-12 08:32:10");
        String page = htmlCreator.programPage(programDto);
        assertThat(page).isEqualTo(
                 "<html><head><title>Code</title></head><body><span id=\"load_date\">2020-12-12 08:32:10</span><pre id=\"code_snippet\">main()</pre></body></html> function send() { " +
                 "let object = { \"code\": document.getElementById(\"code_snippet\").value }; let json = JSON.stringify(object); let xhr = new XMLHttpRequest(); " +
                 "xhr.open(\"POST\", '/api/code/new', false); xhr.setRequestHeader('Content-type', 'application/json; charset=utf-8');" +
                 "xhr.send(json); if (xhr.status == 200) { alert(\"Success!\"); } }");
    }
}