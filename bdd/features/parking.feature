Feature: Test rest /parking

  Scenario: Get :8080/parking/
    Given a request url http://127.0.0.1:8080/parking/
    When the request sends GET
    Then the response status is OK
      And the response json matches
          """
          {
            "type": "array",
            "items": [
              {
                "type": "object",
                "properties": {
                  "id": {"type": "number"},
                  "address": {"type": "string"}
                },
                "required": ["id", "address"]
               }
            ]
          }
          """
