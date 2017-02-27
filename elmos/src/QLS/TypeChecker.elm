module QLS.TypeChecker exposing (check)

import QL.AST exposing (Form)
import QLS.AST exposing (StyleSheet)
import QLS.TypeChecker.InvalidQuestionReferences as InvalidQuestionReferences
import QLS.TypeChecker.Messages exposing (Message)


{-| TODO
 - [x] no references to questions that are not in the QL program
 - [ ] all questions of the QL program are placed by the QLS program.
 - [ ] (default) widget assignments are compatible with question types (e.g. no radio button for integer widgets).
 - [ ] you cannot place a single question multiple times.
-}
check : Form -> StyleSheet -> List Message
check form stylesheet =
    List.concat
        [ InvalidQuestionReferences.check form stylesheet
        ]
