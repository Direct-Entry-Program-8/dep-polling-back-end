package lk.ijse.dep8.polling.api;

import jakarta.json.bind.Jsonb;
import jakarta.json.bind.JsonbBuilder;
import jakarta.json.bind.JsonbException;
import lk.ijse.dep8.polling.dto.PollDTO;
import lk.ijse.dep8.polling.util.HttpServlet2;
import lk.ijse.dep8.polling.util.ResponseStatusException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@WebServlet(name = "PollServlet", urlPatterns = "/api/v1/polls/*")
public class PollServlet extends HttpServlet2 {

    private int getPollId(HttpServletRequest req) {
        if (req.getPathInfo() == null) throw new ResponseStatusException(404, "Invalid end point");
        Matcher matcher = Pattern.compile("^/(\\d+)/?$").matcher(req.getPathInfo());
        if (!matcher.find()) throw new ResponseStatusException(404, "Invalid poll id");
        return Integer.parseInt(matcher.group(1));
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (req.getPathInfo() == null || req.getPathInfo().equals("/")) {
            /* Todo: Request all polls from the service layer */
        } else {
            int pollId = getPollId(req);
            /* Todo: Request a poll from the service layer by giving the poll id */
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        /* Validate the URL */
        if (req.getPathInfo() != null && !req.getPathInfo().equals("/")) {
            throw new ResponseStatusException(404, "Invalid end point");
        }
        /* Validate the content type */
        if (req.getContentType() == null || !req.getContentType().toLowerCase()
                .startsWith("application/json")) {
            throw new ResponseStatusException(415, "Invalid content type");
        }
        try {
            /* Convert json -> PollDTO */
            Jsonb jsonb = JsonbBuilder.create();
            PollDTO pollDTO = jsonb.fromJson(req.getReader(), PollDTO.class);

            /* Validate pollDTO */
            if (pollDTO.getId() != null) {
                throw new ResponseStatusException(400, "Id should be empty");
            } else if (pollDTO.getCreatedBy() == null || pollDTO.getCreatedBy().trim().isEmpty()) {
                throw new ResponseStatusException(400, "Invalid user");
            } else if ((pollDTO.getUpVotes() != null && pollDTO.getUpVotes() != 0) ||
                    (pollDTO.getDownVotes() != null && pollDTO.getDownVotes() != 0)) {
                throw new ResponseStatusException(400, "Votes count should be zero");
            } else if (pollDTO.getTitle() == null || pollDTO.getTitle().trim().isEmpty()) {
                throw new ResponseStatusException(400, "Invalid title");
            }

            /* Todo: Request to save this pollDTO from service layer */
        } catch (JsonbException t) {
            throw new ResponseStatusException(400, "Invalid JSON", t);
        }
    }

    @Override
    protected void doPatch(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        /* Validate the URL */
        int pollId = getPollId(req);

        /* Validate the content type */
        if (req.getContentType() == null || !req.getContentType().toLowerCase()
                .startsWith("application/json")) {
            throw new ResponseStatusException(415, "Invalid content type");
        }
        try {
            /* Convert json -> PollDTO */
            Jsonb jsonb = JsonbBuilder.create();
            PollDTO pollDTO = jsonb.fromJson(req.getReader(), PollDTO.class);

            /* Validate pollDTO */
            if (pollDTO.getId() != null && pollDTO.getId() != pollId) {
                throw new ResponseStatusException(400, "Id mismatched error");
            } else if (pollDTO.getCreatedBy() == null || pollDTO.getCreatedBy().trim().isEmpty()) {
                throw new ResponseStatusException(400, "Invalid user");
            } else if (pollDTO.getUpVotes() == null || pollDTO.getDownVotes() == null) {
                throw new ResponseStatusException(400, "Invalid votes count");
            } else if (pollDTO.getUpVotes() < 0 || pollDTO.getDownVotes() < 0) {
                throw new ResponseStatusException(400, "Votes count can't be negative");
            } else if (pollDTO.getTitle() == null || pollDTO.getTitle().trim().isEmpty()) {
                throw new ResponseStatusException(400, "Invalid title");
            }

            /* Todo: Request to update this pollDTO from service layer */
        } catch (JsonbException t) {
            throw new ResponseStatusException(400, "Invalid JSON", t);
        }
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        /* Validate URL */
        int pollId = getPollId(req);

        /* Todo: Request to delete this poll from service layer */
    }
}
