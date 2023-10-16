const defaultUrl = '/manage/step4';

const paramContainer = {
    step3: {
        url: defaultUrl
        , redirectUrl: '/manage/step3'
        , dataIdNames: 'driveReport'
    },
    step4: {
        url: defaultUrl
        , form: 'optionForm'
        , dataIdNames: 'driveReport'
        , listElementClassName: 'table-tuple'
        , defaultSortingCriteria: 'driveDate'
    }
}

