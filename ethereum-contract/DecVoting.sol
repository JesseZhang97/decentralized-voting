pragma solidity >= 0.6 <0.7;
pragma experimental ABIEncoderV2;

contract DecVoting {
    enum State {SETUP, REGISTRATION, VOTING, READY_TO_TALLY, END_TALLY}
    State public state;
    bool public returnbool = false;
    uint public voteSC = 111;

    modifier inState(State s) {
        if (state != s) {
            revert("vote stage wrong");
        }
        _;
    }

    /****************************************
                SETUP DATA
    /****************************************/
    string public voteName;//投票标题
    uint256 public registrationStartTime; //注册开始时间
    uint256 public registrationEndTime; //注册结束时间
    uint256 public votingStartTime; //投票开始时间
    uint256 public votingEndTime; //投票结束时间
    address[] public voterAddress; //投票人地址列表
    string[] public candidates; //候选人list
    /****************************************
                END SETUP DATA
    /****************************************/




    /****************************************
                REGISTRATION DATA
    /****************************************/
    mapping(address => bool) public registeredVoters;
    /****************************************
                END REGISTRATION DATA
    /****************************************/




    /****************************************
                VOTE DATA
    /****************************************/
    string[] votes;
    mapping(address => bool) public isVoted;
    mapping(string => uint256) public electionResults;
    /****************************************
                END VOTE DATA
    /****************************************/

    constructor() public {
        state = State.SETUP;
    }

    function finishSetUp(
        string memory _voteName,
        uint256 _registrationStartTime,
        uint256 _registrationEndTime,
        uint256 _votingStartTime,
        uint256 _votingEndTime,
        address[] memory _voterAddress,
        string[] memory _candidates
    ) public inState(State.SETUP) {
        voteName = _voteName;
        registrationStartTime = _registrationStartTime;
        registrationEndTime = _registrationEndTime;
        votingStartTime = _votingStartTime;
        votingEndTime = _votingEndTime;
        voterAddress = _voterAddress;
        candidates = _candidates;

        state = State.REGISTRATION;
        returnbool = true;
    }

    function registerVoter(address publickey) public inState(State.REGISTRATION) {
        if(registeredVoters[publickey]) {
                returnbool = false;
            }
        registeredVoters[publickey] = true;
        returnbool = true;
    }

    function endRegistrationPhase() public inState(State.REGISTRATION) returns (bool) {
        state = State.VOTING;
        returnbool = true;
    }

    function castVote (string memory _hashedVotes) public inState(State.VOTING) {
        //未注册不能参与投票
        if (!registeredVoters[msg.sender]) {
            voteSC = 1;
            returnbool = false;
        }
        //已经投票过,不能再次投票
        if (isVoted[msg.sender]) {
            voteSC = 2;
            returnbool = false;
        }
        //成功给出选票
        votes.push(_hashedVotes);
        isVoted[msg.sender] = true;
        returnbool = true;
    }

    function endVotingPhase() public inState(State.VOTING) returns (bool) {
        state = State.READY_TO_TALLY;
        return true;
    }


    function beginTallyPhase() public inState(State.READY_TO_TALLY) returns (bool) {
        for( uint i = 0; i < candidates.length; i++ ) {
            electionResults[candidates[i]] = 0;
        }
        for( uint j = 0; j < votes.length; j++) {
            electionResults[votes[j]] += 1;
        }
        state = State.END_TALLY;
    }
    function numOfVoters() public view returns (uint){
        return voterAddress.length;
    }
    function numOfCandidates() public view returns (uint) {
        return candidates.length;
    }
}
