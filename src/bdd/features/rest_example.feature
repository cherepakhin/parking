Feature: Showing rest

  Scenario: Run a simple rest test :8080/car/1
    Given a request url http://127.0.0.1:8080/car/1
    When the request sends GET
    Then the response status is OK
      And the response json matches
          """
          {
            "type": "object",
            "properties": {
                "id": {"type": "number"},
                "gosNumber": {"type": "string"},
                "model": {"type": "string"}
            },
            "required": ["id", "gosNumber", "model"]
          }
          """