const defaultUrl = '/manage/step4';

const paramContainer = {
    step3: {
        url: defaultUrl
        , redirectUrl: '/manage/step3'
        , dataIdNames: 'mileage'
    },
    step4: {
        url: defaultUrl
        , form: 'optionForm'
        , dataIdNames: 'mileage'
        , listElementClassName: 'table-tuple'
        , defaultSortingCriteria: 'driveDate'
    }
}

