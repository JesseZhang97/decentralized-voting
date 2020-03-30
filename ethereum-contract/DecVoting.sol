pragma solidity >= 0.6 <0.7;
pragma experimental ABIEncoderV2;

contract DecVoting {
    enum State {SETUP, REGISTRATION, VOTING, READY_TO_TALLY, END_TALLY}
    State public state;

    modifier inState(State s) {
        if (state != s) {
            revert("vote stage wrong");
        }
        _;
    }

    /****************************************
                SETUP DATA
    /****************************************/
    //投票参数
    string public voteName;//投票标题
    uint256 public registrationStartTime; //注册开始时间
    uint256 public registrationEndTime; //注册结束时间
    uint256 public votingStartTime; //投票开始时间
    uint256 public votingEndTime; //投票结束时间
    //投票人参数
        address[] public voterAddress; //投票人地址列表
    //uint160[] public voterAddress; //投票人地址列表
    //uint256 public numberOfVoters; //TODO投票人数 前端限制人数
    //候选人参数
    string[] public candidates; //候选人list
    mapping(string => bool) public candidatesMap;
    //TODO 是否对所有人公开 bool public ifPublic;
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
    mapping(address => bool) public isVoted;
    //FIXME datatype zero
    bytes32[] hashedVotes;
    mapping(bytes32 => uint256) public electionResults; 
    /****************************************
                END VOTE DATA
    /****************************************/
    //mapping(string => uint256) public electionResults;

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
    ) public inState(State.SETUP) returns (bool) {
        //参数限制
        if (_registrationStartTime < block.timestamp) {
            return false;
        }

        if (_registrationStartTime > _registrationEndTime) {
            return false;
        }

        if (_registrationEndTime > _votingStartTime ||
            _votingStartTime > _votingEndTime) {
            return false;
        }

        voteName = _voteName;
        registrationStartTime = _registrationStartTime;
        registrationEndTime = _registrationEndTime;
        votingStartTime = _votingStartTime;
        votingEndTime = _votingEndTime;
       // numberOfVoters = _numberOfVoters;
        //numberOfCandidates = _numberOfCandidates;
        voterAddress = _voterAddress;
        candidates = _candidates;

        state = State.REGISTRATION;
        return true;
    }

    //TODO 后端进行过滤,只允许传入符合投票名单的地址
    function registerVoter(address publickey) public inState(State.REGISTRATION)
        returns (bool) {
            if (block.timestamp < registrationStartTime) {
                return false;
            }
            //TODO sha3(publickey)
            else {
                if(registeredVoters[publickey]) {
                    return false;
                }
                else {
                    registeredVoters[publickey] = true;
                    return true;
                }
            }
        }
    
    function endRegistrationPhase() public inState(State.REGISTRATION) returns (bool) {
        //TODO 两种情况结束注册阶段 1、全部投票人注册状态为true 便利mapping 如果全为true则  2、到达注册结束时间
        if (block.timestamp < registrationEndTime) {
            return false;
        }
        state = State.VOTING;
        return true;
    }
    /**
    TODO 选票数据明文存储,投票地址匿名
     */
    function castVote (bytes32 _hashedVotes, address voterAddress) public inState(State.VOTING) returns (uint) {
        //未注册不能参与投票
        if (!registeredVoters[voterAddress]) {
            return 1;
        }
        //已经投票过,不能再次投票
        if (isVoted[voterAddress]) {
            return 2;
        }
        //成功给出选票
        hashedVotes.push(_hashedVotes);
        isVoted[voterAddress] = true;
        return 3;
    }

    //后端计时器到时间后调用此方法改变投票状态
    function endVotingPhase() public inState(State.VOTING) returns (bool) {
        state = State.READY_TO_TALLY;
        return true;
    }


    function beginTallyPhase() public inState(State.READY_TO_TALLY) returns (bool) {
        //TODO 计票

        state = State.END_TALLY;
    }
}
