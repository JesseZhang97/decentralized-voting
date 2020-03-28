pragma solidity >=0.4.22 <0.7.0;


contract ExampleContract {
    mapping(string => uint256) balanceByName;
    event setBalanceEvent(string name, uint256 balance);

    function setBalance(string memory _name, uint256 _balance) public {
        balanceByName[_name] = _balance;
        emit setBalanceEvent(_name, _balance);
    }

    function returnBalance(string memory _name) public view returns (uint256) {
        return balanceByName[_name];
    }
}
