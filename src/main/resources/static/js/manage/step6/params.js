const defaultUrl = '/manage/step6';

const paramContainer = {
    step5: {
        url: defaultUrl
        , redirectUrl: '/manage/step5'
        , dataIdNames: 'mileage'
    },
    step6: {
        url: defaultUrl
        , form: 'optionForm'
        , dataIdNames: 'mileage'
        , listElementClassNames: 'table-tuple'
        , defaultSortingCriteria: 'driveDate'
    }
}

