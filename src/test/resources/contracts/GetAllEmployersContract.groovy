import org.springframework.cloud.contract.spec.Contract

Contract.make {
    description "should Return user with such name"
    request {
        method GET()
        url("/all")
    }
    response {
        body(
                """{
                   "employers": [
        {
            "id": 7839,
            "name": "KING",
            "deptId": 10
        },
        {
            "id": 7698,
            "name": "BLAKE",
            "deptId": 30
        },
        {
            "id": 7782,
            "name": "CLARK",
            "deptId": 10
        }
        ]
        }
                   """
        )
        status 200
        headers {
            contentType('application/json')
        }
    }

}
